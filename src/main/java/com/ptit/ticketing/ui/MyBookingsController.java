package com.ptit.ticketing.ui;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.service.BookingService;
import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller cho My Bookings screen
 */
public class MyBookingsController {

    @FXML
    private VBox bookingsContainer;

    @FXML
    private Label titleLabel;

    @FXML
    private Button backButton;

    @FXML
    private Label emptyLabel;

    private BookingService bookingService;

    @FXML
    public void initialize() {
        bookingService = new BookingService(Database.get().ds());

        if (titleLabel != null) {
            titleLabel.setText("My Bookings - " + SessionManager.getInstance().getCurrentFullName());
        }

        loadBookings();
        System.out.println("✅ MyBookingsController initialized");
    }

    private void loadBookings() {
        try {
            List<Booking> bookings = bookingService.getUserBookings(
                    SessionManager.getInstance().getCurrentUser().getId());

            displayBookings(bookings);
            System.out.println("✅ Loaded " + bookings.size() + " bookings");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load bookings: " + e.getMessage());
        }
    }

    private void displayBookings(List<Booking> bookings) {
        if (bookingsContainer == null)
            return;

        bookingsContainer.getChildren().clear();

        if (bookings.isEmpty()) {
            if (emptyLabel != null) {
                emptyLabel.setVisible(true);
            }
            return;
        }

        if (emptyLabel != null) {
            emptyLabel.setVisible(false);
        }

        for (Booking booking : bookings) {
            VBox bookingCard = createBookingCard(booking);
            bookingsContainer.getChildren().add(bookingCard);
        }
    }

    private VBox createBookingCard(Booking booking) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");
        VBox.setMargin(card, new Insets(10));

        // Header với movie title và status
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        // Hiển thị tên phim thay vì booking ID
        String movieTitle = booking.getMovieTitle() != null ? booking.getMovieTitle() : "Movie";
        Label titleLabel = new Label("🎬 " + movieTitle);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusLabel = new Label(booking.getStatus().toUpperCase());
        String statusColor = getStatusColor(booking.getStatus());
        statusLabel.setStyle(
                "-fx-background-color: " + statusColor + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 5 15; " +
                        "-fx-background-radius: 5; " +
                        "-fx-font-weight: bold;");

        header.getChildren().addAll(titleLabel, spacer, statusLabel);

        // Booking info
        VBox info = new VBox(8);

        // Hiển thị giờ chiếu
        if (booking.getShowtimeStart() != null) {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                    .ofPattern("dd/MM/yyyy HH:mm");
            String showtimeStr = booking.getShowtimeStart().format(formatter);
            Label showtimeLabel = new Label("🕐 Showtime: " + showtimeStr);
            showtimeLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");
            info.getChildren().add(showtimeLabel);
        }

        Label priceLabel = new Label("💰 Total: " + String.format("%.0f VND", booking.getTotalAmount().doubleValue()));
        priceLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        String paymentMethod = booking.getPaymentMethod();
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            paymentMethod = "PENDING";
        } else {
            paymentMethod = paymentMethod.toUpperCase();
        }
        Label paymentLabel = new Label("💳 Payment: " + paymentMethod);
        paymentLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        // Hiển thị danh sách ghế thay vì booking ID
        String seats = bookingService.getBookingSeats(booking.getId());
        Label seatsLabel = new Label("💺 Seats: " + (seats.isEmpty() ? "N/A" : seats));
        seatsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #3498db;");

        info.getChildren().addAll(priceLabel, paymentLabel, seatsLabel);

        // Action buttons
        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_RIGHT);

        // Nút Continue Payment cho booking pending
        if ("pending".equalsIgnoreCase(booking.getStatus())) {
            Button payButton = new Button("Continue Payment");
            payButton.setStyle(
                    "-fx-background-color: #27ae60; " +
                            "-fx-text-fill: white; " +
                            "-fx-padding: 8 20; " +
                            "-fx-background-radius: 5; " +
                            "-fx-cursor: hand; " +
                            "-fx-font-weight: bold;");
            payButton.setOnAction(e -> continuePayment(booking));
            actions.getChildren().add(payButton);
        }

        Button detailsButton = new Button("View Details");
        detailsButton.setStyle(
                "-fx-background-color: #3498db; " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 8 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;");
        detailsButton.setOnAction(e -> showBookingDetails(booking));

        actions.getChildren().add(detailsButton);

        card.getChildren().addAll(header, new Separator(), info, actions);

        return card;
    }

    private String getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "paid":
            case "confirmed":
                return "#27ae60";
            case "pending":
                return "#f39c12";
            case "cancelled":
                return "#e74c3c";
            default:
                return "#95a5a6";
        }
    }

    private void continuePayment(Booking booking) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Continue Payment");
        confirmAlert.setHeaderText("Complete Your Booking");
        confirmAlert.setContentText("Total amount: " + String.format("%.0f VND", booking.getTotalAmount().doubleValue())
                + "\n\nProceed with payment?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // Update booking status to paid
                    bookingService.updateBookingStatus(booking.getId(), "paid", "cash");

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText("Payment Completed!");
                    successAlert.setContentText("Your booking has been confirmed.\nBooking ID: "
                            + booking.getId().toString().substring(0, 8));
                    successAlert.showAndWait();

                    // Reload bookings
                    loadBookings();

                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Failed to process payment: " + e.getMessage());
                }
            }
        });
    }

    private void showBookingDetails(Booking booking) {
        try {
            // Tạo dialog đẹp hơn với custom content
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Booking Details");
            dialog.setHeaderText(null);

            // Create custom content
            VBox content = new VBox(20);
            content.setPadding(new Insets(20));
            content.setStyle("-fx-background-color: white;");

            // Header với tên phim
            String movieTitle = booking.getMovieTitle() != null ? booking.getMovieTitle() : "Movie";
            Label titleLabel = new Label("🎬 " + movieTitle);
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

            // Status badge
            String statusColor = getStatusColor(booking.getStatus());
            Label statusBadge = new Label(booking.getStatus().toUpperCase());
            statusBadge.setStyle(
                    "-fx-background-color: " + statusColor + "; " +
                            "-fx-text-fill: white; " +
                            "-fx-padding: 8 20; " +
                            "-fx-background-radius: 5; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 14px;");

            HBox headerBox = new HBox(15, titleLabel, statusBadge);
            headerBox.setAlignment(Pos.CENTER_LEFT);

            // Details grid
            GridPane grid = new GridPane();
            grid.setHgap(20);
            grid.setVgap(15);
            grid.setPadding(new Insets(20, 0, 0, 0));

            int row = 0;

            // Hiển thị giờ chiếu
            if (booking.getShowtimeStart() != null) {
                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm");
                String showtimeStr = booking.getShowtimeStart().format(formatter);
                addDetailRow(grid, row++, "🕐 Showtime:", showtimeStr);
            }

            // Hiển thị danh sách ghế
            String seats = bookingService.getBookingSeats(booking.getId());
            addDetailRow(grid, row++, "💺 Seats:", seats.isEmpty() ? "N/A" : seats);

            addDetailRow(grid, row++, "💰 Total Amount:",
                    String.format("%.0f VND", booking.getTotalAmount().doubleValue()));

            String paymentMethod = booking.getPaymentMethod();
            if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
                paymentMethod = "Pending";
            }
            addDetailRow(grid, row++, "💳 Payment Method:", paymentMethod.toUpperCase());
            addDetailRow(grid, row++, "📅 Booked At:", booking.getCreatedAt().toLocalDateTime()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            addDetailRow(grid, row++, "⏰ Expires At:", booking.getExpiresAt().toLocalDateTime()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

            content.getChildren().addAll(headerBox, new Separator(), grid);

            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.getDialogPane().setPrefSize(600, 500);

            dialog.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to show details: " + e.getMessage());
        }
    }

    private void addDetailRow(GridPane grid, int row, String label, String value) {
        Label labelNode = new Label(label);
        labelNode.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #7f8c8d;");

        Label valueNode = new Label(value);
        valueNode.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        valueNode.setWrapText(true);
        valueNode.setMaxWidth(350);

        grid.add(labelNode, 0, row);
        grid.add(valueNode, 1, row);
    }

    @FXML
    private void handleBack() {
        try {
            // Check user role để quay về màn hình phù hợp
            boolean isAdmin = SessionManager.getInstance().isAdmin();

            String fxmlPath = isAdmin ? "/ui/dashboard.fxml" : "/ui/movie-list.fxml";
            String title = isAdmin ? "Dashboard" : "Browse Movies";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - " + title);

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
            showAlert("Error", "Failed to load Settings");
        }
    }

    @FXML
    private void handleLogout() {
        try {
            // Logout from session
            SessionManager.getInstance().logout();

            // Navigate back to login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.setTitle("Cinema Management System - Login");
            stage.centerOnScreen();

            System.out.println("✅ Logged out successfully");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to logout: " + e.getMessage());
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
