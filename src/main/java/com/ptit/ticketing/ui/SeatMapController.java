package com.ptit.ticketing.ui;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.domain.Showtime;
import com.ptit.ticketing.service.BookingService;
import com.ptit.ticketing.service.SeatService;
import com.ptit.ticketing.service.SessionManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controller cho seat map screen - SIMPLIFIED MVP
 */
public class SeatMapController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label showtimeInfoLabel;

    @FXML
    private GridPane seatGrid;

    @FXML
    private Label selectedSeatsLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Button backButton;

    @FXML
    private Label timerLabel;

    private Showtime currentShowtime;
    private Movie currentMovie;
    private SeatService seatService;
    private BookingService bookingService;
    private List<Seat> allSeats;
    private Set<Seat> selectedSeats = new HashSet<>();

    // Real-time updates
    private ScheduledExecutorService seatUpdateScheduler;
    private Map<UUID, Button> seatButtonMap = new HashMap<>();
    private Set<UUID> currentlyBookedSeats = new HashSet<>();
    private static final int REFRESH_INTERVAL_SECONDS = 5; // Refresh mỗi 5 giây

    // Giá theo loại ghế
    private static final BigDecimal STANDARD_PRICE = new BigDecimal("50000");
    private static final BigDecimal VIP_PRICE = new BigDecimal("80000");
    private static final BigDecimal COUPLE_PRICE = new BigDecimal("150000");

    @FXML
    public void initialize() {
        seatService = new SeatService(Database.get().ds());
        bookingService = new BookingService(Database.get().ds());

        // Start booking timer khi vào màn hình chọn ghế
        startBookingTimer();

        System.out.println("✅ SeatMapController initialized");
    }

    /**
     * Start real-time seat availability monitoring
     */
    private void startSeatUpdateScheduler() {
        if (seatUpdateScheduler != null && !seatUpdateScheduler.isShutdown()) {
            return; // Already running
        }

        seatUpdateScheduler = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "SeatUpdateThread");
            thread.setDaemon(true); // Daemon thread tự động stop khi app close
            return thread;
        });

        // Schedule refresh mỗi 5 giây
        seatUpdateScheduler.scheduleAtFixedRate(() -> {
            try {
                refreshSeatAvailability();
            } catch (Exception e) {
                System.err.println("❌ Error refreshing seat availability: " + e.getMessage());
                e.printStackTrace();
            }
        }, REFRESH_INTERVAL_SECONDS, REFRESH_INTERVAL_SECONDS, TimeUnit.SECONDS);

        System.out.println("✅ Seat availability auto-refresh started (every " + REFRESH_INTERVAL_SECONDS + " seconds)");
    }

    /**
     * Stop seat update scheduler
     */
    private void stopSeatUpdateScheduler() {
        if (seatUpdateScheduler != null && !seatUpdateScheduler.isShutdown()) {
            seatUpdateScheduler.shutdown();
            System.out.println("🛑 Seat availability auto-refresh stopped");
        }
    }

    /**
     * Refresh seat availability từ database
     */
    private void refreshSeatAvailability() {
        if (currentShowtime == null)
            return;

        try {
            // Get currently booked seats from database
            Set<UUID> newBookedSeats = seatService.getBookedSeatIds(currentShowtime.getId());

            // Check for changes
            Set<UUID> newlyBooked = new HashSet<>(newBookedSeats);
            newlyBooked.removeAll(currentlyBookedSeats); // Seats booked by others

            Set<UUID> newlyFreed = new HashSet<>(currentlyBookedSeats);
            newlyFreed.removeAll(newBookedSeats); // Seats that became free

            if (!newlyBooked.isEmpty() || !newlyFreed.isEmpty()) {
                // Update UI on JavaFX thread
                Platform.runLater(() -> {
                    updateSeatButtons(newlyBooked, newlyFreed);
                    currentlyBookedSeats = newBookedSeats;
                });
            }

        } catch (Exception e) {
            System.err.println("❌ Error in refreshSeatAvailability: " + e.getMessage());
        }
    }

    /**
     * Update seat buttons khi có thay đổi availability
     */
    private void updateSeatButtons(Set<UUID> newlyBooked, Set<UUID> newlyFreed) {
        int changedCount = 0;

        // Mark newly booked seats as unavailable
        for (UUID seatId : newlyBooked) {
            Button btn = seatButtonMap.get(seatId);
            if (btn != null && !btn.isDisabled()) {
                // Ghế vừa được book bởi user khác
                btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                        "-fx-background-radius: 5; -fx-font-weight: bold;");
                btn.setDisable(true);

                // Remove from selected seats nếu user đang chọn
                Seat seat = findSeatById(seatId);
                if (seat != null && selectedSeats.contains(seat)) {
                    selectedSeats.remove(seat);
                    showAlert("Seat Unavailable",
                            String.format("Seat %s%d has been booked by another user",
                                    seat.getRowLabel(), seat.getSeatNumber()));
                }
                changedCount++;
            }
        }

        // Mark newly freed seats as available
        for (UUID seatId : newlyFreed) {
            Button btn = seatButtonMap.get(seatId);
            Seat seat = findSeatById(seatId);
            if (btn != null && seat != null) {
                // Ghế vừa được release (booking cancelled/expired)
                String seatType = seat.getSeatType().toLowerCase();
                String color = switch (seatType) {
                    case "vip" -> "#f39c12";
                    case "couple" -> "#e91e63";
                    default -> "#2ecc71";
                };

                btn.setStyle(String.format(
                        "-fx-background-color: %s; -fx-text-fill: white; " +
                                "-fx-background-radius: 5; -fx-cursor: hand; -fx-font-weight: bold;",
                        color));
                btn.setDisable(false);
                btn.setOnAction(e -> toggleSeatSelection(seat, btn));
                changedCount++;
            }
        }

        if (changedCount > 0) {
            updateSummary();
            System.out.println("🔄 Updated " + changedCount + " seat(s) availability");
        }
    }

    /**
     * Find seat by ID từ allSeats list
     */
    private Seat findSeatById(UUID seatId) {
        if (allSeats == null)
            return null;
        return allSeats.stream()
                .filter(s -> s.getId().equals(seatId))
                .findFirst()
                .orElse(null);
    }

    public void setShowtime(Showtime showtime, Movie movie) {
        this.currentShowtime = showtime;
        this.currentMovie = movie;
        loadShowtimeInfo();
        loadSeats();

        // Start real-time seat monitoring
        startSeatUpdateScheduler();
    }

    private void loadShowtimeInfo() {
        if (movieTitleLabel != null) {
            movieTitleLabel.setText(currentMovie.getTitle());
        }

        if (showtimeInfoLabel != null) {
            String info = String.format("%s | %s | %.0f VND",
                    currentShowtime.getStartTime().toString(),
                    currentShowtime.getAuditoriumName(),
                    currentShowtime.getBasePrice());
            showtimeInfoLabel.setText(info);
        }
    }

    private void loadSeats() {
        try {
            allSeats = seatService.getSeatsByAuditorium(currentShowtime.getAuditoriumId());
            displaySeats();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load seats: " + e.getMessage());
        }
    }

    private void displaySeats() {
        if (seatGrid == null)
            return;

        seatGrid.getChildren().clear();
        seatButtonMap.clear(); // Clear button map
        currentlyBookedSeats.clear(); // Clear booked seats cache

        seatGrid.setHgap(8);
        seatGrid.setVgap(8);
        seatGrid.setPadding(new Insets(20));

        // Group by row
        int currentRow = 0;
        String lastRowLabel = "";

        for (Seat seat : allSeats) {
            if (!seat.getRowLabel().equals(lastRowLabel)) {
                // New row
                currentRow++;
                lastRowLabel = seat.getRowLabel();

                // Row label
                Label rowLabel = new Label(seat.getRowLabel());
                rowLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-min-width: 30px;");
                seatGrid.add(rowLabel, 0, currentRow);
            }

            Button seatButton = createSeatButton(seat);

            // Store button reference cho real-time updates
            seatButtonMap.put(seat.getId(), seatButton);

            // Tính column index: ghế couple chiếm 2 cột nên nhân 2
            int seatCol;
            if ("couple".equalsIgnoreCase(seat.getSeatType())) {
                // Ghế couple: mỗi ghế chiếm vị trí của 2 ghế thường
                seatCol = (seat.getSeatNumber() - 1) * 2 + 1;
                GridPane.setColumnSpan(seatButton, 2);
            } else {
                // Ghế thường: dùng seat number trực tiếp
                seatCol = seat.getSeatNumber();
            }

            seatGrid.add(seatButton, seatCol, currentRow);

            // Track initially booked seats
            boolean isBooked = !seatService.isSeatAvailable(currentShowtime.getId(), seat.getId());
            if (isBooked) {
                currentlyBookedSeats.add(seat.getId());
            }
        }

        updateSummary();
    }

    private Button createSeatButton(Seat seat) {
        Button btn = new Button(String.valueOf(seat.getSeatNumber()));

        // Kích thước và style theo loại ghế
        String seatType = seat.getSeatType().toLowerCase();
        if ("couple".equals(seatType)) {
            // Ghế couple = 2 ghế thường (45px) + 1 gap (8px) = 98px
            btn.setPrefSize(98, 45);
            btn.setMinSize(98, 45);
            btn.setMaxSize(98, 45);
        } else {
            btn.setPrefSize(45, 45);
            btn.setMinSize(45, 45);
            btn.setMaxSize(45, 45);
        }

        // Check if booked
        boolean isBooked = !seatService.isSeatAvailable(currentShowtime.getId(), seat.getId());

        if (isBooked) {
            btn.setStyle(
                    "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-background-radius: 5; -fx-font-weight: bold;");
            btn.setDisable(true);
        } else {
            // Màu sắc theo loại ghế
            String color = switch (seatType) {
                case "vip" -> "#f39c12"; // Vàng cho VIP
                case "couple" -> "#e91e63"; // Hồng cho Couple
                default -> "#2ecc71"; // Xanh lá cho Standard
            };

            btn.setStyle(String.format(
                    "-fx-background-color: %s; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand; -fx-font-weight: bold;",
                    color));
            btn.setOnAction(e -> toggleSeatSelection(seat, btn));
        }

        return btn;
    }

    private void toggleSeatSelection(Seat seat, Button btn) {
        String seatType = seat.getSeatType().toLowerCase();

        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            // Trả về màu gốc
            String color = switch (seatType) {
                case "vip" -> "#f39c12";
                case "couple" -> "#e91e63";
                default -> "#2ecc71";
            };
            btn.setStyle(String.format(
                    "-fx-background-color: %s; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand; -fx-font-weight: bold;",
                    color));
        } else {
            selectedSeats.add(seat);
            btn.setStyle(
                    "-fx-background-color: #3498db; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand; -fx-font-weight: bold;");
        }
        updateSummary();
    }

    private BigDecimal getSeatPrice(Seat seat) {
        return switch (seat.getSeatType().toLowerCase()) {
            case "vip" -> VIP_PRICE;
            case "couple" -> COUPLE_PRICE;
            default -> STANDARD_PRICE;
        };
    }

    private void updateSummary() {
        if (selectedSeatsLabel != null) {
            List<String> seatLabels = new ArrayList<>();
            for (Seat s : selectedSeats) {
                String type = s.getSeatType().toUpperCase();
                seatLabels.add(s.getRowLabel() + s.getSeatNumber() + "(" + type + ")");
            }
            selectedSeatsLabel.setText("Selected: " + String.join(", ", seatLabels));
        }

        if (totalPriceLabel != null) {
            BigDecimal total = BigDecimal.ZERO;
            for (Seat seat : selectedSeats) {
                total = total.add(getSeatPrice(seat));
            }
            totalPriceLabel.setText(String.format("Total: %.0f VND", total.doubleValue()));
        }

        if (confirmButton != null) {
            confirmButton.setDisable(selectedSeats.isEmpty());
        }
    }

    @FXML
    private void handleConfirmBooking() {
        if (selectedSeats.isEmpty()) {
            showAlert("Error", "Please select at least one seat");
            return;
        }

        // Stop seat update scheduler khi chuyển sang payment
        stopSeatUpdateScheduler();

        // Calculate total với giá theo từng loại ghế
        BigDecimal total = BigDecimal.ZERO;
        for (Seat seat : selectedSeats) {
            total = total.add(getSeatPrice(seat));
        }

        // Navigate to Payment screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/payment.fxml"));
            Parent root = loader.load();

            // Pass booking data to PaymentController
            PaymentController controller = loader.getController();
            controller.setBookingData(currentShowtime, currentMovie, new ArrayList<>(selectedSeats), total);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Payment - " + currentMovie.getTitle());

            System.out.println("✅ Navigated to Payment screen");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load payment screen: " + e.getMessage());
        }
    }

    private void navigateToMyBookings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/my-bookings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("My Bookings");

            System.out.println("✅ Navigated to My Bookings");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load My Bookings: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        // Stop timer và seat update scheduler khi back
        stopSeatUpdateScheduler();
        com.ptit.ticketing.service.SessionTimer.getInstance().stopTimer();

        try {
            // Quay về Showtime List với movie hiện tại
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/showtime-list.fxml"));
            Parent root = loader.load();

            // Pass movie back to ShowtimeListController
            ShowtimeListController controller = loader.getController();
            controller.setMovie(currentMovie);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Select Showtime - " + currentMovie.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to go back: " + e.getMessage());
        }
    }

    /**
     * Start booking timer (10 phút)
     */
    private void startBookingTimer() {
        com.ptit.ticketing.service.SessionTimer.getInstance().startTimer(
                // Callback mỗi giây - update UI
                remainingSeconds -> {
                    if (timerLabel != null) {
                        timerLabel.setText(com.ptit.ticketing.service.SessionTimer.getInstance().getFormattedTime());

                        // Đổi màu khi còn < 1 phút
                        if (remainingSeconds < 60) {
                            timerLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 24px; -fx-font-weight: bold;");
                        } else if (remainingSeconds < 180) { // < 3 phút
                            timerLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-size: 24px; -fx-font-weight: bold;");
                        }
                    }
                },
                // Callback khi timeout - logout ra chọn phim
                this::handleTimeout);
    }

    /**
     * Handle timeout - Reset về màn hình chọn phim
     */
    private void handleTimeout() {
        // Stop seat update scheduler khi timeout
        stopSeatUpdateScheduler();

        System.out.println("⏰ TIMEOUT! Session expired - navigating to movie list");

        // Show alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Session Expired");
        alert.setHeaderText("⏰ Booking Time Expired");
        alert.setContentText("Your booking session has expired.\nPlease start again from selecting a movie.");
        alert.showAndWait();

        // Navigate to movie list
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/movie-list.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Browse Movies");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
