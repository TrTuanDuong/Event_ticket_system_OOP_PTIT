package com.ptit.ticketing.ui;

import com.ptit.ticketing.auth.DjangoPassword;
import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.domain.User;
import com.ptit.ticketing.service.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ProfileSettingsController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @FXML
    private void initialize() {
        loadUserInfo();
    }

    private void loadUserInfo() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            usernameLabel.setText(currentUser.getUsername());
            usernameField.setText(currentUser.getUsername());
            emailField.setText(currentUser.getEmail() != null ? currentUser.getEmail() : "");
            fullNameField.setText(currentUser.getFullName() != null ? currentUser.getFullName() : "");
            phoneField.setText(currentUser.getPhone() != null ? currentUser.getPhone() : "");
        }
    }

    @FXML
    private void handleUpdateInfo() {
        hideMessage();

        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String phone = phoneField.getText().trim();

        // Validate email
        if (email.isEmpty()) {
            showError("Email is required");
            emailField.requestFocus();
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            showError("Please enter a valid email address");
            emailField.requestFocus();
            return;
        }

        // Update database
        User currentUser = SessionManager.getCurrentUser();
        String sql = "UPDATE api_user SET email = ?, full_name = ?, phone = ?, " +
                "first_name = ?, last_name = ?, updated_at = ? WHERE id = ?";

        try (Connection conn = Database.get().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Parse full name
            String firstName = "";
            String lastName = "";
            if (fullName != null && !fullName.isEmpty()) {
                String[] nameParts = fullName.trim().split("\\s+", 2);
                firstName = nameParts[0];
                if (nameParts.length > 1) {
                    lastName = nameParts[1];
                }
            } else {
                firstName = currentUser.getUsername();
            }

            stmt.setString(1, email);
            stmt.setString(2, fullName.isEmpty() ? currentUser.getUsername() : fullName);
            stmt.setString(3, phone.isEmpty() ? null : phone);
            stmt.setString(4, firstName);
            stmt.setString(5, lastName.isEmpty() ? "" : lastName);
            stmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setObject(7, currentUser.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Update session
                currentUser.setEmail(email);
                currentUser.setFullName(fullName.isEmpty() ? currentUser.getUsername() : fullName);
                currentUser.setPhone(phone.isEmpty() ? null : phone);

                showSuccess("✅ Profile updated successfully!");
            } else {
                showError("Failed to update profile");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("An error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleChangePassword() {
        hideMessage();

        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate inputs
        if (currentPassword.isEmpty()) {
            showError("Please enter your current password");
            currentPasswordField.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            showError("Please enter a new password");
            newPasswordField.requestFocus();
            return;
        }

        if (newPassword.length() < 6) {
            showError("New password must be at least 6 characters long");
            newPasswordField.requestFocus();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showError("New passwords do not match");
            confirmPasswordField.requestFocus();
            return;
        }

        User currentUser = SessionManager.getCurrentUser();

        // Verify current password
        if (!DjangoPassword.verify(currentPassword, currentUser.getPassword())) {
            showError("Current password is incorrect");
            currentPasswordField.requestFocus();
            return;
        }

        // Update password in database
        String sql = "UPDATE api_user SET password = ?, updated_at = ? WHERE id = ?";

        try (Connection conn = Database.get().getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            DjangoPassword djangoPassword = new DjangoPassword();
            String hashedPassword = djangoPassword.hashPassword(newPassword);

            stmt.setString(1, hashedPassword);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setObject(3, currentUser.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Update session
                currentUser.setPassword(hashedPassword);

                showSuccess("✅ Password changed successfully!");

                // Clear password fields
                currentPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
            } else {
                showError("Failed to change password");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showError("An error occurred: " + e.getMessage());
        }
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

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - " + title);
        } catch (Exception e) {
            e.printStackTrace();
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

            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.setTitle("Cinema Management System - Login");
            stage.centerOnScreen();

            System.out.println("✅ Logged out successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        messageLabel.setText("❌ " + message);
        messageLabel.setStyle("-fx-text-fill: #e74c3c; -fx-background-color: #fee; " +
                "-fx-padding: 10; -fx-background-radius: 5;");
        messageLabel.setVisible(true);
    }

    private void showSuccess(String message) {
        messageLabel.setText(message);
        messageLabel.setStyle("-fx-text-fill: #27ae60; -fx-background-color: #e8f8f5; " +
                "-fx-padding: 10; -fx-background-radius: 5;");
        messageLabel.setVisible(true);
    }

    private void hideMessage() {
        messageLabel.setVisible(false);
    }
}
