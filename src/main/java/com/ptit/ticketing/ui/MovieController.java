package com.ptit.ticketing.ui;

import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.service.MovieService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;

public class MovieController implements Initializable {

    @FXML private TextField searchField;
    @FXML private TableView<Movie> movieTableView;
    @FXML private TableColumn<Movie, String> titleColumn;
    @FXML private TableColumn<Movie, Integer> durationColumn;
    @FXML private TableColumn<Movie, String> ratingColumn;
    @FXML private TableColumn<Movie, LocalDate> releaseDateColumn;

    private MovieService movieService = new MovieService();
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();
    private FilteredList<Movie> filteredMovieList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("durationMin"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        loadMovies();

        // Thêm bộ lọc cho tìm kiếm
        filteredMovieList = new FilteredList<>(movieList, p -> true);
        movieTableView.setItems(filteredMovieList);
    }

    @FXML
    void handleSearch() {
        String filter = searchField.getText();
        if (filter == null || filter.isEmpty()) {
            filteredMovieList.setPredicate(p -> true);
        } else {
            String lowerCaseFilter = filter.toLowerCase();
            filteredMovieList.setPredicate(movie -> 
                movie.getTitle().toLowerCase().contains(lowerCaseFilter)
            );
        }
    }

    @FXML
    void handleAddMovie() {
        showMovieForm(null);
    }

    @FXML
    void handleEditMovie() {
        Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            showMovieForm(selectedMovie);
        } else {
            showAlert("Chưa chọn phim", "Vui lòng chọn một phim để sửa.");
        }
    }

    @FXML
    void handleDeleteMovie() {
        Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa");
            alert.setHeaderText("Bạn có chắc muốn xóa phim: " + selectedMovie.getTitle() + "?");
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                movieService.deleteMovie(selectedMovie.getId());
                loadMovies(); // Tải lại danh sách
            }
        } else {
            showAlert("Chưa chọn phim", "Vui lòng chọn một phim để xóa.");
        }
    }

    private void loadMovies() {
        movieList.clear();
        movieList.addAll(movieService.getAllMovies());
    }

    private void showMovieForm(Movie movie) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/movie-form.fxml"));
            Parent root = loader.load();

            MovieFormController controller = loader.getController();
            controller.setMovie(movie); // Truyền phim (nếu là edit)

            Stage dialogStage = new Stage();
            dialogStage.setTitle(movie == null ? "Thêm Phim Mới" : "Sửa Thông Tin Phim");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));
            
            // Đợi dialog đóng rồi tải lại
            dialogStage.showAndWait();
            loadMovies();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}