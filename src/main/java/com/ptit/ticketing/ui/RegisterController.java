package com.ptit.ticketing.ui;

import com.ptit.ticketing.auth.DjangoPassword;
import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.User;
import com.ptit.ticketing.service.SessionManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

public class RegisterController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button registerButton;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @FXML
    private void initialize() {
        // Auto-focus username field
        Platform.runLater(() -> usernameField.requestFocus());
    }

    @FXML
    private void handleRegister() {
        // Clear previous messages
        hideMessage();

        // Get input values
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate inputs
        if (username.isEmpty()) {
            showError("Please enter a username");
            usernameField.requestFocus();
            return;
        }

        if (username.length() < 3) {
            showError("Username must be at least 3 characters long");
            usernameField.requestFocus();
            return;
        }

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            showError("Username can only contain letters, numbers, and underscores");
            usernameField.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            showError("Please enter an email address");
            emailField.requestFocus();
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showError("Please enter a valid email address");
            emailField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showError("Please enter a password");
            passwordField.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showError("Password must be at least 6 characters long");
            passwordField.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match");
            confirmPasswordField.requestFocus();
            return;
        }

        // Check if username or email already exists
        if (isUsernameTaken(username)) {
            showError("Username already exists. Please choose another one.");
            usernameField.requestFocus();
            return;
        }

        if (isEmailTaken(email)) {
            showError("Email already registered. Please use another email or login.");
            emailField.requestFocus();
            return;
        }

        // Disable register button to prevent double-click
        registerButton.setDisable(true);

        try {
            // Create new user
            User newUser = createUser(username, email, password, fullName, phone);

            if (newUser != null) {
                // Show success message
                showSuccess("Account created successfully! Logging in...");

                // Auto-login and navigate to dashboard
                SessionManager.getInstance().login(newUser);

                // Delay navigation to show success message
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1000);
                        navigateToDashboard();
                    } catch (Exception e) {
                        e.printStackTrace();
                        navigateToDashboard();
                    }
                });
            } else {
                showError("Failed to create account. Please try again.");
                registerButton.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("An error occurred: " + e.getMessage());
            registerButton.setDisable(false);
        }
    }

    private boolean isUsernameTaken(String username) {
        String sql = "SELECT COUNT(*) FROM api_user WHERE username = ?";
        try (Connection conn = Database.get().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isEmailTaken(String email) {
        String sql = "SELECT COUNT(*) FROM api_user WHERE email = ?";
        try (Connection conn = Database.get().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private User createUser(String username, String email, String password,
            String fullName, String phone) {
        String sql = "INSERT INTO api_user (id, username, email, password, full_name, phone, " +
                "first_name, last_name, is_active, is_staff, is_superuser, role, " +
                "date_joined, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.get().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            UUID userId = UUID.randomUUID();
            LocalDateTime now = LocalDateTime.now();

            // Hash password using Django-compatible method
            DjangoPassword djangoPassword = new DjangoPassword();
            String hashedPassword = djangoPassword.hashPassword(password);

            // Parse full name into first and last name
            String firstName = "";
            String lastName = "";
            if (fullName != null && !fullName.isEmpty()) {
                String[] nameParts = fullName.trim().split("\\s+", 2);
                firstName = nameParts[0];
                if (nameParts.length > 1) {
                    lastName = nameParts[1];
                }
            }

            // If no full name provided, use username
            if (firstName.isEmpty()) {
                firstName = username;
            }

            stmt.setObject(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            stmt.setString(5, fullName != null && !fullName.isEmpty() ? fullName : username);
            stmt.setString(6, phone);
            stmt.setString(7, firstName);
            stmt.setString(8, lastName.isEmpty() ? "" : lastName);
            stmt.setBoolean(9, true); // is_active
            stmt.setBoolean(10, false); // is_staff - người dùng thường
            stmt.setBoolean(11, false); // is_superuser - không phải superuser
            stmt.setString(12, "user"); // role - vai trò người dùng
            stmt.setTimestamp(13, Timestamp.valueOf(now)); // date_joined
            stmt.setTimestamp(14, Timestamp.valueOf(now)); // created_at
            stmt.setTimestamp(15, Timestamp.valueOf(now)); // updated_at

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Return the newly created user
                User user = new User();
                user.setId(userId);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(hashedPassword);
                user.setFullName(fullName);
                user.setPhone(phone);
                user.setActive(true);
                user.setStaff(false);
                user.setDateJoined(LocalDateTime.now());
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML
    private void handleBackToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cinema Management System - Login");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to go back to login screen");
        }
    }

    private void navigateToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cinema Management System - Dashboard");
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to navigate to dashboard");
            registerButton.setDisable(false);
        }
    }

    private void showError(String message) {
        messageLabel.setText("❌ " + message);
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-background-color: #fee; " +
                "-fx-padding: 10; -fx-background-radius: 5;");
        messageLabel.setVisible(true);
    }

    private void showSuccess(String message) {
        messageLabel.setText("✅ " + message);
        messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-background-color: #e8f8f5; " +
                "-fx-padding: 10; -fx-background-radius: 5;");
        messageLabel.setVisible(true);
    }

    private void hideMessage() {
        messageLabel.setVisible(false);
    }
}
