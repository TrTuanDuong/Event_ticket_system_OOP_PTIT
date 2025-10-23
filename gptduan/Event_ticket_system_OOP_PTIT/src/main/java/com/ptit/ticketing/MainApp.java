package com.ptit.ticketing;

import com.ptit.ticketing.config.Database;
import com.ptit.ticketing.repo.MovieRepo;
import com.ptit.ticketing.repo.ShowtimeRepo;
import com.ptit.ticketing.util.Tx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainApp extends Application {
  @Override
  public void start(Stage stage) {
    try {
      var ds = Database.get().ds();
      var movieRepo = new MovieRepo(ds);
      var showtimeRepo = new ShowtimeRepo(ds);

      // Create UI
      VBox root = new VBox(10);
      root.setStyle("-fx-padding: 20;");

      // Movies section
      Label moviesLabel = new Label("📽️ MOVIES IN SYSTEM:");
      moviesLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
      ListView<String> moviesList = new ListView<>();

      // Showtimes section
      Label showtimesLabel = new Label("\n⏰ UPCOMING SHOWTIMES:");
      showtimesLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
      ListView<String> showtimesList = new ListView<>();

      // Load data
      var movies = Tx.withTx(ds, c -> movieRepo.findAll(c));
      movies.forEach(m -> moviesList.getItems().add(
          m.getTitle() + " (" + m.getDurationMin() + " min)"));

      var showtimes = Tx.withTx(ds, c -> showtimeRepo.findUpcoming(c));
      showtimes.forEach(s -> showtimesList.getItems().add(
          s.getMovieTitle() + " @ " + s.getAuditoriumName() +
              " - " + s.getStartTime().toString()));

      root.getChildren().addAll(
          moviesLabel, moviesList,
          showtimesLabel, showtimesList);

      stage.setScene(new Scene(root, 800, 600));
      stage.setTitle("Cinema Management System - Connected to Django DB");
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
