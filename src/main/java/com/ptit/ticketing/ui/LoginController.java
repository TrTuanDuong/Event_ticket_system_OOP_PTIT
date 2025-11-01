package com.ptit.ticketing.ui;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.User;
import com.ptit.ticketing.service.AuthService;
import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller cho màn hình đăng nhập
 */
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private CheckBox rememberMeCheckbox;

    private AuthService authService;

    @FXML
    public void initialize() {
        // Khởi tạo AuthService
        authService = new AuthService(Database.get().ds());

        // Ẩn error label ban đầu
        if (errorLabel != null) {
            errorLabel.setVisible(false);
        }

        // Enter key để login
        if (passwordField != null) {
            passwordField.setOnAction(event -> handleLogin());
        }

        System.out.println("✅ LoginController initialized");
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Validation
        if (username.isEmpty()) {
            showError("Please enter username");
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showError("Please enter password");
            passwordField.requestFocus();
            return;
        }

        // Disable login button để tránh spam
        loginButton.setDisable(true);

        try {
            // Verify login credentials
            Optional<User> userOpt = authService.login(username, password);

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                // Lưu vào session
                SessionManager.getInstance().login(user);

                System.out.println("✅ Login successful: " + user.getUsername());

                // Check role và chuyển đến màn hình phù hợp
                if (user.isStaff()) {
                    // Admin → Dashboard
                    navigateToDashboard();
                } else {
                    // User thường → Browse Movies
                    navigateToMovieList();
                }

            } else {
                showError("Invalid username or password");
                passwordField.clear();
                passwordField.requestFocus();
            }

        } catch (Exception e) {
            showError("Login failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            loginButton.setDisable(false);
        }
    }

    @FXML
    private void handleCancel() {
        // Đóng ứng dụng
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleRegister() {
        try {
            // Load register screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/register.fxml"));
            Parent root = loader.load();

            // Get current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cinema Management - Register");
            stage.centerOnScreen();

            System.out.println("✅ Navigated to register screen");

        } catch (Exception e) {
            System.err.println("❌ Error loading register screen:");
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Register screen");
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setVisible(true);

            // Auto hide sau 3 giây
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    javafx.application.Platform.runLater(() -> {
                        if (errorLabel != null) {
                            errorLabel.setVisible(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void navigateToMovieList() {
        try {
            // Load movie list screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/movie-list.fxml"));
            Parent root = loader.load();

            // Get current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Set new scene with fixed size to avoid window jumping
            Scene scene = new Scene(root, 1400, 900);
            stage.setScene(scene);
            stage.setTitle("Cinema Management - Browse Movies");

            // Center on screen to maintain position
            stage.centerOnScreen();

            System.out.println("✅ Navigated to movie list");

        } catch (Exception e) {
            // Print detailed error
            System.err.println("❌ Error loading movie list:");
            e.printStackTrace();

            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Movie List");
            alert.setContentText("Error: " + e.getMessage() + "\n\nPlease check console for details.");
            alert.showAndWait();
        }
    }

    private void navigateToDashboard() {
        try {
            // Load dashboard screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));
            Parent root = loader.load();

            // Get current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Set new scene with fixed size to avoid window jumping
            Scene scene = new Scene(root, 1400, 900);
            stage.setScene(scene);
            stage.setTitle("Cinema Management - Dashboard");

            // Center on screen to maintain position
            stage.centerOnScreen();

            System.out.println("✅ Navigated to dashboard");

        } catch (Exception e) {
            // Print detailed error
            System.err.println("❌ Error loading dashboard:");
            e.printStackTrace();

            // Show error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load Dashboard");
            alert.setContentText("Error: " + e.getMessage() + "\n\nPlease check console for details.");
            alert.showAndWait();
        }
    }
}
