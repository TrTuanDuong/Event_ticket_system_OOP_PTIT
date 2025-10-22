package com.ptit.ticketing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class TestReportsUI extends Application {
  @Override
  public void start(Stage stage) {
    try {
      // ✅ Sửa lại đường dẫn đến đúng vị trí thật của file
      URL fxmlUrl = getClass().getClassLoader().getResource("ui/reports.fxml");

      if (fxmlUrl == null) {
        throw new IllegalStateException("❌ Không tìm thấy ui/reports.fxml trong resources!");
      }

      FXMLLoader loader = new FXMLLoader(fxmlUrl);
      Parent root = loader.load();

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("📊 Reports & Analytics");
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
