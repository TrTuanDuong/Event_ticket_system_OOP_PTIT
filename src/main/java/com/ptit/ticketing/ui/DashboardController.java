package com.ptit.ticketing.ui;

import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller cho màn hình Dashboard chính
 */
public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Button browseMoviesButton;

    @FXML
    private Button myBookingsButton;

    @FXML
    private VBox adminButton;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Set welcome message
        SessionManager session = SessionManager.getInstance();
        if (welcomeLabel != null) {
            welcomeLabel.setText("Welcome back, " + session.getCurrentFullName() + "!");
        }

        if (roleLabel != null) {
            String role = session.isAdmin() ? "Administrator" : "Customer";
            roleLabel.setText("Role: " + role);
        }

        // Show/hide admin button based on role
        if (adminButton != null) {
            adminButton.setVisible(session.isAdmin());
            adminButton.setManaged(session.isAdmin());
        }

        System.out.println("✅ DashboardController initialized for: " + session.getCurrentUsername());
    }

    @FXML
    private void handleBrowseMovies() {
        try {
            navigateTo("/ui/movie-list.fxml", "Browse Movies", 1200, 800);
        } catch (IOException e) {
            showAlert("Feature Coming Soon", "Movie browsing will be available soon!");
        }
    }

    @FXML
    private void handleMyBookings() {
        try {
            navigateTo("/ui/my-bookings.fxml", "My Bookings", 1200, 800);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load My Bookings: " + e.getMessage());
        }
    }

    @FXML
    private void handleSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/profile-settings.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Profile Settings");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Settings: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdmin() {
        try {
            navigateTo("/ui/admin-panel.fxml", "Admin Panel", 1200, 800);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Admin Panel: " + e.getMessage());
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

            Stage stage = (Stage) logoutButton.getScene().getWindow();
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

    private void navigateTo(String fxmlPath, String title, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        Stage stage = (Stage) browseMoviesButton.getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.setTitle("Cinema Management - " + title);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
