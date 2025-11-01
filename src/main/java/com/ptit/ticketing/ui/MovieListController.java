package com.ptit.ticketing.ui;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.service.MovieService;
import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller cho màn hình browse movies
 */
public class MovieListController {

    @FXML
    private TextField searchField;

    @FXML
    private FlowPane moviesContainer;

    @FXML
    private Label titleLabel;

    @FXML
    private Button backButton;

    private MovieService movieService;
    private List<Movie> currentMovies;

    @FXML
    public void initialize() {
        // Initialize service
        movieService = new MovieService(Database.get().ds());

        // Set welcome message
        if (titleLabel != null) {
            titleLabel.setText("Browse Movies - " + SessionManager.getInstance().getCurrentFullName());
        }

        // Show/hide back button based on user role
        // Admin có thể quay về Dashboard, User thường thì không
        if (backButton != null) {
            boolean isAdmin = SessionManager.getInstance().isAdmin();
            backButton.setVisible(isAdmin);
            backButton.setManaged(isAdmin);
        }

        // Load all movies
        loadMovies();

        // Setup search listener
        if (searchField != null) {
            searchField.textProperty().addListener((obs, oldVal, newVal) -> {
                handleSearch();
            });
        }

        System.out.println("✅ MovieListController initialized");
    }

    private void loadMovies() {
        try {
            currentMovies = movieService.getAllMovies();
            displayMovies(currentMovies);
            System.out.println("✅ Loaded " + currentMovies.size() + " movies");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load movies: " + e.getMessage());
        }
    }

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText();
        try {
            currentMovies = movieService.searchMovies(keyword);
            displayMovies(currentMovies);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Search failed: " + e.getMessage());
        }
    }

    @FXML
    private void handleMyBookings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/my-bookings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - My Bookings");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load My Bookings");
        }
    }

    @FXML
    private void handleBack() {
        try {
            // Chỉ admin mới có nút Back, quay về Dashboard
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Dashboard");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to go back");
        }
    }

    @FXML
    private void handleSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/profile-settings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) searchField.getScene().getWindow();
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

            Stage stage = (Stage) searchField.getScene().getWindow();
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

    private void displayMovies(List<Movie> movies) {
        if (moviesContainer == null) {
            System.err.println("❌ moviesContainer is NULL!");
            return;
        }

        moviesContainer.getChildren().clear();

        if (movies.isEmpty()) {
            Label noResults = new Label("No movies found");
            noResults.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d;");
            moviesContainer.getChildren().add(noResults);
            return;
        }

        System.out.println("📋 Displaying " + movies.size() + " movie cards");
        for (Movie movie : movies) {
            VBox movieCard = createMovieCard(movie);
            moviesContainer.getChildren().add(movieCard);
        }
    }

    private VBox createMovieCard(Movie movie) {
        VBox card = new VBox(15);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(250);
        card.setPrefHeight(400);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3); " +
                        "-fx-cursor: hand;");

        // Hover effect
        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(102,126,234,0.4), 15, 0, 0, 5); " +
                        "-fx-cursor: hand; " +
                        "-fx-scale-x: 1.02; " +
                        "-fx-scale-y: 1.02;"));

        card.setOnMouseExited(e -> card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3); " +
                        "-fx-cursor: hand;"));

        // Movie poster
        StackPane posterPane = new StackPane();
        posterPane.setPrefSize(210, 280);
        posterPane.setMaxSize(210, 280);
        posterPane.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 10;");

        ImageView posterImage = new ImageView();
        posterImage.setFitWidth(210);
        posterImage.setFitHeight(280);
        posterImage.setPreserveRatio(false);
        posterImage.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        if (movie.getPosterUrl() != null && !movie.getPosterUrl().trim().isEmpty()) {
            try {
                Image image = new Image(movie.getPosterUrl(), true);
                posterImage.setImage(image);
                posterPane.getChildren().add(posterImage);
            } catch (Exception e) {
                Label placeholder = new Label("🎬");
                placeholder.setStyle("-fx-font-size: 80px;");
                posterPane.getChildren().add(placeholder);
            }
        } else {
            Label placeholder = new Label("🎬");
            placeholder.setStyle("-fx-font-size: 80px;");
            posterPane.getChildren().add(placeholder);
        }

        // Movie title
        Label titleLabel = new Label(movie.getTitle());
        titleLabel.setFont(Font.font("System Bold", 16));
        titleLabel.setStyle("-fx-text-fill: #2c3e50; -fx-text-alignment: center;");
        titleLabel.setWrapText(true);
        titleLabel.setMaxWidth(220);

        // Movie info
        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER);

        Label durationLabel = new Label("⏱️ " + movie.getDurationMin() + " min");
        durationLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        Label ratingLabel = new Label("🎯 " + (movie.getRating() != null ? movie.getRating() : "Not Rated"));
        ratingLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        Label releaseDateLabel = new Label(
                "📅 " + (movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "TBA"));
        releaseDateLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        infoBox.getChildren().addAll(durationLabel, ratingLabel, releaseDateLabel);

        // Description (truncated)
        Label descLabel = new Label(truncateText(movie.getDescription(), 100));
        descLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(220);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Book button
        Button bookButton = new Button("View Showtimes");
        bookButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #667eea, #764ba2); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 13px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        bookButton.setOnAction(e -> handleSelectMovie(movie));

        card.getChildren().addAll(posterPane, titleLabel, infoBox, descLabel, spacer, bookButton);

        return card;
    }

    private void handleSelectMovie(Movie movie) {
        try {
            // Navigate to showtime selection
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/showtime-list.fxml"));
            Parent root = loader.load();

            // Pass movie to next controller
            ShowtimeListController controller = loader.getController();
            controller.setMovie(movie);

            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Select Showtime - " + movie.getTitle());

        } catch (IOException e) {
            e.printStackTrace();
            // Fallback to alert if showtime screen not ready
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Movie Selected");
            alert.setHeaderText(movie.getTitle());
            alert.setContentText(
                    "Duration: " + movie.getDurationMin() + " minutes\n" +
                            "Rating: " + (movie.getRating() != null ? movie.getRating() : "Not Rated") + "\n\n" +
                            movie.getDescription() + "\n\n" +
                            "Showtime selection screen coming soon...");
            alert.showAndWait();
        }
    }

    private String truncateText(String text, int maxLength) {
        if (text == null)
            return "";
        if (text.length() <= maxLength)
            return text;
        return text.substring(0, maxLength) + "...";
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
