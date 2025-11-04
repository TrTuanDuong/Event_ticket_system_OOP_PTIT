package com.ptit.ticketing.ui;

import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.domain.Showtime;
import com.ptit.ticketing.service.BookingService;
import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller cho màn hình thanh toán
 */
public class PaymentController {

    @FXML
    private Button backButton;

    @FXML
    private Label bookingInfoLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label movieLabel;

    @FXML
    private Label showtimeLabel;

    @FXML
    private Label auditoriumLabel;

    @FXML
    private Label seatsLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Button qrButton;

    @FXML
    private Button cashButton;

    @FXML
    private VBox qrCodeContainer;

    @FXML
    private javafx.scene.image.ImageView qrImageView;

    @FXML
    private Label qrAmountLabel;

    private Showtime currentShowtime;
    private Movie currentMovie;
    private List<Seat> selectedSeats;
    private BigDecimal totalAmount;
    private String selectedPaymentMethod;
    private BookingService bookingService;

    @FXML
    public void initialize() {
        bookingService = new BookingService(com.ptit.ticketing.config.Database.get().ds());

        // Continue timer from SeatMap
        if (com.ptit.ticketing.service.SessionTimer.getInstance().isRunning()) {
            updateTimerDisplay();
        }

        System.out.println("✅ PaymentController initialized");
    }

    /**
     * Set booking data từ SeatMapController
     */
    public void setBookingData(Showtime showtime, Movie movie, List<Seat> seats, BigDecimal total) {
        this.currentShowtime = showtime;
        this.currentMovie = movie;
        this.selectedSeats = seats;
        this.totalAmount = total;

        displayBookingInfo();
    }

    private void displayBookingInfo() {
        if (currentMovie != null) {
            movieLabel.setText(currentMovie.getTitle());
        }

        if (currentShowtime != null) {
            // Format thời gian với timezone để hiển thị đúng giờ địa phương
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                    .ofPattern("dd/MM/yyyy HH:mm");
            showtimeLabel.setText(currentShowtime.getStartTime().format(formatter));
            auditoriumLabel.setText(currentShowtime.getAuditoriumName());
        }

        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            List<String> seatLabels = new ArrayList<>();
            for (Seat s : selectedSeats) {
                seatLabels.add(s.getRowLabel() + s.getSeatNumber());
            }
            seatsLabel.setText(String.join(", ", seatLabels));
        }

        if (totalAmount != null) {
            totalLabel.setText(String.format("%.0f VND", totalAmount.doubleValue()));
            qrAmountLabel.setText(String.format("Amount: %.0f VND", totalAmount.doubleValue()));
        }
    }

    @FXML
    private void handleSelectQRPayment() {
        selectedPaymentMethod = "qr_code";

        // Show QR code container
        qrCodeContainer.setVisible(true);
        qrCodeContainer.setManaged(true);

        // Load QR code image
        try {
            String imagePath = getClass().getResource("/ImageView/qr-payment.png").toExternalForm();
            javafx.scene.image.Image qrImage = new javafx.scene.image.Image(imagePath);
            qrImageView.setImage(qrImage);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể load ảnh QR code: " + e.getMessage());
        }

        System.out.println("✅ Selected payment method: QR Code");
    }

    @FXML
    private void handleSelectCashPayment() {
        selectedPaymentMethod = "cash";

        // Hide QR code container
        qrCodeContainer.setVisible(false);
        qrCodeContainer.setManaged(false);

        // Confirm cash payment
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Cash Payment");
        confirmAlert.setHeaderText("Confirm Cash Payment");
        confirmAlert.setContentText(
                "You will pay at the cinema counter.\n\n" +
                        "Movie: " + currentMovie.getTitle() + "\n" +
                        "Total: " + String.format("%.0f VND", totalAmount.doubleValue()) + "\n\n" +
                        "Continue with booking?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                handleConfirmPayment();
            }
        });

        System.out.println("✅ Selected payment method: Cash");
    }

    /**
     * Update timer display continuously
     */
    private void updateTimerDisplay() {
        com.ptit.ticketing.service.SessionTimer timer = com.ptit.ticketing.service.SessionTimer.getInstance();

        if (timerLabel != null && timer.isRunning()) {
            // Update initial display
            timerLabel.setText(timer.getFormattedTime());

            // Register callback để update UI
            timer.startTimer(
                    remainingSeconds -> {
                        javafx.application.Platform.runLater(() -> {
                            if (timerLabel != null) {
                                timerLabel.setText(timer.getFormattedTime());

                                // Color coding
                                if (remainingSeconds < 60) {
                                    // Đỏ khi < 1 phút
                                    timerLabel.setStyle(
                                            "-fx-text-fill: #e74c3c; -fx-font-size: 24px; -fx-font-weight: bold;");
                                } else if (remainingSeconds < 180) {
                                    // Cam khi < 3 phút
                                    timerLabel.setStyle(
                                            "-fx-text-fill: #f39c12; -fx-font-size: 24px; -fx-font-weight: bold;");
                                } else {
                                    // Bình thường
                                    timerLabel.setStyle(
                                            "-fx-text-fill: #e74c3c; -fx-font-size: 24px; -fx-font-weight: bold;");
                                }
                            }
                        });
                    },
                    this::handleTimeout);
        }
    }

    /**
     * Handle timer timeout
     */
    private void handleTimeout() {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Session Expired");
            alert.setHeaderText("⏰ Booking Time Expired");
            alert.setContentText(
                    "Your booking session has expired.\n" +
                            "Please start again from selecting a movie.");
            alert.showAndWait();

            // Navigate back to movie list
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/movie-list.fxml"));
                javafx.scene.layout.BorderPane root = loader.load();

                javafx.stage.Stage stage = (javafx.stage.Stage) backButton.getScene().getWindow();
                stage.getScene().setRoot(root);

                System.out.println("⏰ Timeout - Returned to movie list");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleConfirmPayment() {
        if (selectedPaymentMethod == null) {
            showAlert("Error", "Please select a payment method");
            return;
        }

        try {
            // Stop timer khi payment thành công
            com.ptit.ticketing.service.SessionTimer.getInstance().stopTimer();

            // Create booking với payment method
            Booking booking = bookingService.createBooking(
                    SessionManager.getCurrentUser().getId(),
                    currentShowtime.getId(),
                    selectedSeats,
                    totalAmount,
                    selectedPaymentMethod // Pass payment method
            );

            // Show success message dựa vào payment method
            List<String> seatLabels = new ArrayList<>();
            for (Seat s : selectedSeats) {
                seatLabels.add(s.getRowLabel() + s.getSeatNumber());
            }

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);

            if ("qr_code".equals(selectedPaymentMethod)) {
                // QR payment - pending approval
                // Format thời gian đúng
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm");

                successAlert.setTitle("Payment Request Sent!");
                successAlert.setHeaderText("⏳ Waiting for Admin Approval");
                successAlert.setContentText(
                        "Booking ID: " + booking.getId() + "\n\n" +
                                "Movie: " + currentMovie.getTitle() + "\n" +
                                "Time: " + currentShowtime.getStartTime().format(formatter) + "\n" +
                                "Auditorium: " + currentShowtime.getAuditoriumName() + "\n\n" +
                                "Seats: " + String.join(", ", seatLabels) + "\n" +
                                "Total: " + String.format("%.0f VND", totalAmount.doubleValue()) + "\n\n" +
                                "Payment Method: QR CODE\n" +
                                "Status: PENDING APPROVAL\n\n" +
                                "⏳ Your booking will be confirmed once the admin approves your QR payment.");
            } else {
                // Cash payment - confirmed immediately
                // Format thời gian đúng
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm");

                successAlert.setTitle("Payment Successful!");
                successAlert.setHeaderText("✅ Your booking has been confirmed");
                successAlert.setContentText(
                        "Booking ID: " + booking.getId() + "\n\n" +
                                "Movie: " + currentMovie.getTitle() + "\n" +
                                "Time: " + currentShowtime.getStartTime().format(formatter) + "\n" +
                                "Auditorium: " + currentShowtime.getAuditoriumName() + "\n\n" +
                                "Seats: " + String.join(", ", seatLabels) + "\n" +
                                "Total: " + String.format("%.0f VND", totalAmount.doubleValue()) + "\n\n" +
                                "Payment Method: CASH\n" +
                                "Status: " + booking.getStatus().toUpperCase() + "\n\n" +
                                "💵 Please pay at the cinema counter.");
            }

            successAlert.showAndWait();

            System.out.println("✅ Payment confirmed - Booking created: " + booking.getId());

            // Navigate to My Bookings
            navigateToMyBookings();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Payment Failed", "Error processing payment: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancelPayment() {
        selectedPaymentMethod = null;
        qrCodeContainer.setVisible(false);
        qrCodeContainer.setManaged(false);
        System.out.println("❌ Payment cancelled");
    }

    @FXML
    private void handleBack() {
        try {
            // Quay về Seat Map
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SeatMap.fxml"));
            Parent root = loader.load();

            // Pass data back
            SeatMapController controller = loader.getController();
            controller.setShowtime(currentShowtime, currentMovie);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Select Seats - " + currentMovie.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to go back: " + e.getMessage());
        }
    }

    @FXML
    private void handleSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/profile-settings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Profile Settings");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Settings: " + e.getMessage());
        }
    }

    @FXML
    private void handleLogout() {
        try {
            SessionManager.getInstance().logout();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management System - Login");

            System.out.println("✅ Logged out successfully");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to logout: " + e.getMessage());
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
