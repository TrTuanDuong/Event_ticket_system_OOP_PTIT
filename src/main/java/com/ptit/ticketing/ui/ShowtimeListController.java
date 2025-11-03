package com.ptit.ticketing.ui;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.domain.Showtime;
import com.ptit.ticketing.service.ShowtimeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller cho màn hình chọn showtime
 */
public class ShowtimeListController {

    @FXML
    private Label movieTitleLabel;

    @FXML
    private Label movieInfoLabel;

    @FXML
    private VBox datesContainer;

    @FXML
    private VBox showtimesContainer;

    @FXML
    private Button backButton;

    private Movie currentMovie;
    private ShowtimeService showtimeService;
    private List<OffsetDateTime> availableDates;
    private OffsetDateTime selectedDate;

    @FXML
    public void initialize() {
        showtimeService = new ShowtimeService(Database.get().ds());
        System.out.println("✅ ShowtimeListController initialized");
    }

    /**
     * Set movie từ MovieListController
     */
    public void setMovie(Movie movie) {
        System.out.println("🎬 setMovie called with: " + movie.getTitle());
        this.currentMovie = movie;
        loadMovieInfo();
        loadAvailableDates();
    }

    private void loadMovieInfo() {
        System.out.println("📝 Loading movie info...");
        if (movieTitleLabel != null) {
            movieTitleLabel.setText(currentMovie.getTitle());
        }

        if (movieInfoLabel != null) {
            String info = String.format("%d min | %s | %s",
                    currentMovie.getDurationMin(),
                    currentMovie.getRating() != null ? currentMovie.getRating() : "Not Rated",
                    currentMovie.getReleaseDate() != null ? currentMovie.getReleaseDate().toString() : "TBA");
            movieInfoLabel.setText(info);
        }
    }

    private void loadAvailableDates() {
        try {
            System.out.println("📅 Loading available dates for movie ID: " + currentMovie.getId());
            availableDates = showtimeService.getAvailableDates(currentMovie.getId());
            System.out.println("📅 Found " + availableDates.size() + " dates");
            displayDates();

            // Auto select first date
            if (!availableDates.isEmpty()) {
                selectDate(availableDates.get(0));
            }

        } catch (Exception e) {
            System.err.println("❌ Error loading dates:");
            e.printStackTrace();
            showAlert("Error", "Failed to load showtimes: " + e.getMessage());
        }
    }

    private void displayDates() {
        System.out.println("🔨 displayDates called, datesContainer null? " + (datesContainer == null));
        if (datesContainer == null)
            return;

        datesContainer.getChildren().clear();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd");

        for (OffsetDateTime date : availableDates) {
            Button dateButton = new Button(date.format(dateFormatter));
            dateButton.setMaxWidth(Double.MAX_VALUE);
            dateButton.setPrefHeight(50);
            dateButton.setStyle(
                    "-fx-background-color: white; " +
                            "-fx-background-radius: 8; " +
                            "-fx-font-size: 14px; " +
                            "-fx-cursor: hand; " +
                            "-fx-border-color: #dddddd; " +
                            "-fx-border-radius: 8; " +
                            "-fx-border-width: 2;");

            dateButton.setOnAction(e -> selectDate(date));

            VBox.setMargin(dateButton, new Insets(5));
            datesContainer.getChildren().add(dateButton);
        }
    }

    private void selectDate(OffsetDateTime date) {
        this.selectedDate = date;

        // Update button styles
        for (javafx.scene.Node node : datesContainer.getChildren()) {
            if (node instanceof Button) {
                Button btn = (Button) node;
                if (isSameDay(date, parseButtonDate(btn.getText()))) {
                    btn.setStyle(
                            "-fx-background-color: linear-gradient(to right, #667eea, #764ba2); " +
                                    "-fx-text-fill: white; " +
                                    "-fx-background-radius: 8; " +
                                    "-fx-font-size: 14px; " +
                                    "-fx-font-weight: bold; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-border-width: 0;");
                } else {
                    btn.setStyle(
                            "-fx-background-color: white; " +
                                    "-fx-background-radius: 8; " +
                                    "-fx-font-size: 14px; " +
                                    "-fx-cursor: hand; " +
                                    "-fx-border-color: #dddddd; " +
                                    "-fx-border-radius: 8; " +
                                    "-fx-border-width: 2;");
                }
            }
        }

        // Load showtimes for selected date
        loadShowtimesForDate(date);
    }

    private void loadShowtimesForDate(OffsetDateTime date) {
        try {
            List<Showtime> showtimes = showtimeService.getShowtimesByDate(currentMovie.getId(), date);
            displayShowtimes(showtimes);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load showtimes: " + e.getMessage());
        }
    }

    private void displayShowtimes(List<Showtime> showtimes) {
        if (showtimesContainer == null)
            return;

        showtimesContainer.getChildren().clear();

        // Filter out các suất chiếu đã bắt đầu
        OffsetDateTime now = OffsetDateTime.now();
        List<Showtime> availableShowtimes = showtimes.stream()
                .filter(st -> st.getStartTime().isAfter(now))
                .toList();

        if (availableShowtimes.isEmpty()) {
            Label noShowtimes = new Label("Không có suất chiếu nào khả dụng cho ngày này");
            noShowtimes.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
            showtimesContainer.getChildren().add(noShowtimes);
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Showtime showtime : availableShowtimes) {
            VBox showtimeCard = createShowtimeCard(showtime, timeFormatter);
            showtimesContainer.getChildren().add(showtimeCard);
            VBox.setMargin(showtimeCard, new Insets(10));
        }
    }

    private VBox createShowtimeCard(Showtime showtime, DateTimeFormatter timeFormatter) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3); " +
                        "-fx-cursor: hand;");

        // Time
        Label timeLabel = new Label("🕐 " + showtime.getStartTime().format(timeFormatter));
        timeLabel.setFont(Font.font("System Bold", 18));
        timeLabel.setStyle("-fx-text-fill: #667eea;");

        // Auditorium
        Label auditoriumLabel = new Label("📍 " + showtime.getAuditoriumName());
        auditoriumLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        // Price
        Label priceLabel = new Label("💰 " + String.format("%.0f VND", showtime.getBasePrice()));
        priceLabel.setFont(Font.font("System Bold", 16));
        priceLabel.setStyle("-fx-text-fill: #27ae60;");

        // Available seats
        int availableSeats = showtimeService.getAvailableSeatsCount(showtime.getId());
        Label seatsLabel = new Label("🪑 " + availableSeats + " seats available");
        seatsLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #7f8c8d;");

        // Book button
        Button bookButton = new Button("Select Seats");
        bookButton.setMaxWidth(Double.MAX_VALUE);
        bookButton.setPrefHeight(40);
        bookButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #667eea, #764ba2); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        bookButton.setOnAction(e -> handleSelectShowtime(showtime));

        card.getChildren().addAll(timeLabel, auditoriumLabel, priceLabel, seatsLabel, bookButton);

        return card;
    }

    private void handleSelectShowtime(Showtime showtime) {
        // Double-check: Kiểm tra xem suất chiếu đã bắt đầu chưa (safety check)
        OffsetDateTime now = OffsetDateTime.now();
        if (showtime.getStartTime().isBefore(now)) {
            showAlert("Không thể đặt vé",
                    "⚠️ Suất chiếu này đã bắt đầu!\n\n" +
                            "Vui lòng chọn suất chiếu khác.");
            return;
        }

        try {
            // Navigate to seat map
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/SeatMap.fxml"));
            Parent root = loader.load();

            // Pass showtime to SeatMapController
            SeatMapController controller = loader.getController();
            controller.setShowtime(showtime, currentMovie);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Select Seats - " + currentMovie.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Coming Soon", "Seat selection will be available soon!");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/movie-list.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Browse Movies");

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
            // Logout from session
            com.ptit.ticketing.service.SessionManager.getInstance().logout();

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

    private boolean isSameDay(OffsetDateTime dt1, OffsetDateTime dt2) {
        return dt1.getYear() == dt2.getYear() &&
                dt1.getMonthValue() == dt2.getMonthValue() &&
                dt1.getDayOfMonth() == dt2.getDayOfMonth();
    }

    private OffsetDateTime parseButtonDate(String text) {
        // Simple parse - just use selectedDate as reference
        return selectedDate != null ? selectedDate : OffsetDateTime.now();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
