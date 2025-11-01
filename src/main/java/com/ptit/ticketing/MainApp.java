package com.ptit.ticketing;

import com.ptit.ticketing.config.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX Application
 * Entry point: Login Screen
 */
public class MainApp extends Application {

  @Override
  public void start(Stage stage) {
    try {
      // Initialize database connection
      var ds = Database.get().ds();

      if (ds == null) {
        System.err.println("⚠️ Running in OFFLINE mode - Database not connected");
      }

      // Load login screen
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/login.fxml"));
      Parent root = loader.load();

      // Setup scene
      Scene scene = new Scene(root, 900, 600);
      stage.setScene(scene);
      stage.setTitle("Cinema Management System - Login");
      stage.setResizable(false);
      stage.centerOnScreen();
      stage.show();

      System.out.println("✅ Application started - Login screen loaded");

    } catch (Exception e) {
      System.err.println("❌ Failed to start application");
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
