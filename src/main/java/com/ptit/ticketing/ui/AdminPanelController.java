package com.ptit.ticketing.ui;

import com.ptit.ticketing.domain.Genre;
import com.ptit.ticketing.domain.Movie;
import com.ptit.ticketing.domain.User;
import com.ptit.ticketing.service.MovieService;
import com.ptit.ticketing.service.SessionManager;
import javafx.application.Platform;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class AdminPanelController {

    @FXML
    private Button backButton;
    @FXML
    private Label adminNameLabel;

    // Movie Management
    @FXML
    private TextField movieSearchField;
    @FXML
    private VBox movieListContainer;

    // User Management
    @FXML
    private TextField userSearchField;
    @FXML
    private VBox userListContainer;

    // Showtime Management
    @FXML
    private VBox showtimeListContainer;
    @FXML
    private Button filterAllBtn;
    @FXML
    private Button filterUpcomingBtn;
    @FXML
    private Button filterOngoingBtn;
    @FXML
    private Button filterCompletedBtn;

    // Auditorium Management
    @FXML
    private VBox auditoriumListContainer;

    // Booking Management
    @FXML
    private VBox bookingListContainer;

    // Pending Approvals (QR Payment)
    @FXML
    private VBox pendingApprovalsContainer;

    // Statistics
    @FXML
    private Label totalMoviesLabel;
    @FXML
    private Label totalUsersLabel;
    @FXML
    private Label totalBookingsLabel;

    private MovieService movieService;
    private com.ptit.ticketing.service.BookingService bookingService;
    private com.ptit.ticketing.service.ShowtimeService showtimeService;
    private com.ptit.ticketing.service.ReportService reportService;
    private List<Movie> allMovies;
    private List<User> allUsers;
    private List<Genre> allGenres;
    private String currentShowtimeFilter = "all"; // all, upcoming, ongoing, completed

    @FXML
    public void initialize() {
        movieService = new MovieService();
        bookingService = new com.ptit.ticketing.service.BookingService(
                com.ptit.ticketing.config.Database.get().ds());
        showtimeService = new com.ptit.ticketing.service.ShowtimeService(
                com.ptit.ticketing.config.Database.get().ds());
        reportService = new com.ptit.ticketing.service.ReportService(
                com.ptit.ticketing.config.Database.get().ds());

        User currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            adminNameLabel.setText("Admin: " + currentUser.getUsername());
        }

        loadGenres();
        loadMovies();
        loadUsers();
        loadShowtimes();
        loadAuditoriums();
        loadBookings();
        loadStatistics();
        loadPendingApprovals();
    }

    // ==================== MOVIE MANAGEMENT ====================

    private void loadGenres() {
        allGenres = new ArrayList<>();
        String sql = "SELECT id, name FROM api_genre ORDER BY name";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(UUID.fromString(rs.getString("id")));
                genre.setName(rs.getString("name"));
                allGenres.add(genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMovies() {
        allMovies = movieService.getAllMovies();
        displayMovies(allMovies);
    }

    private void displayMovies(List<Movie> movies) {
        movieListContainer.getChildren().clear();

        if (movies.isEmpty()) {
            Label emptyLabel = new Label("Không tìm thấy phim nào");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
            movieListContainer.getChildren().add(emptyLabel);
            return;
        }

        for (Movie movie : movies) {
            VBox movieCard = createMovieCard(movie);
            movieListContainer.getChildren().add(movieCard);
        }
    }

    private VBox createMovieCard(Movie movie) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        // Main HBox containing poster and info
        HBox mainContent = new HBox(20);
        mainContent.setAlignment(Pos.TOP_LEFT);

        // Poster Image
        VBox posterBox = new VBox(5);
        posterBox.setAlignment(Pos.TOP_CENTER);

        ImageView posterImage = new ImageView();
        posterImage.setFitWidth(120);
        posterImage.setFitHeight(180);
        posterImage.setPreserveRatio(true);
        posterImage.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 2);");

        if (movie.getPosterUrl() != null && !movie.getPosterUrl().trim().isEmpty()) {
            try {
                Image image = new Image(movie.getPosterUrl(), true); // true = load in background
                posterImage.setImage(image);
            } catch (Exception e) {
                // If image fails to load, show placeholder
                Label noImage = new Label("No\nPoster");
                noImage.setStyle("-fx-font-size: 14px; -fx-text-fill: #95a5a6; -fx-alignment: center;");
                posterBox.getChildren().add(noImage);
            }
        } else {
            Label noImage = new Label("No\nPoster");
            noImage.setStyle("-fx-font-size: 14px; -fx-text-fill: #95a5a6; -fx-alignment: center;");
            posterBox.getChildren().add(noImage);
        }

        if (posterImage.getImage() != null) {
            posterBox.getChildren().add(posterImage);
        }

        // Info VBox
        VBox infoBox = new VBox(10);
        HBox.setHgrow(infoBox, Priority.ALWAYS);

        // Title Row
        HBox titleRow = new HBox(15);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("🎬 " + movie.getTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Action Buttons
        Button editBtn = new Button("✏️ Sửa");
        editBtn.setStyle(
                "-fx-background-color: #f39c12; -fx-text-fill: white; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;");
        editBtn.setOnAction(e -> handleEditMovie(movie));

        Button deleteBtn = new Button("🗑️ Xóa");
        deleteBtn.setStyle(
                "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;");
        deleteBtn.setOnAction(e -> handleDeleteMovie(movie));

        titleRow.getChildren().addAll(titleLabel, spacer, editBtn, deleteBtn);

        // Info Grid
        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(20);
        infoGrid.setVgap(8);

        // Get genres for this movie
        String genresStr = getMovieGenres(movie.getId());

        addInfoRow(infoGrid, 0, "Thể loại:", genresStr);
        addInfoRow(infoGrid, 1, "Đạo diễn:", movie.getDirector());
        addInfoRow(infoGrid, 2, "Thời lượng:", movie.getDuration() + " phút");
        addInfoRow(infoGrid, 3, "Rating:", movie.getRating());
        addInfoRow(infoGrid, 4, "Ngày phát hành:",
                movie.getReleaseDate() != null ? movie.getReleaseDate().toString() : "N/A");
        addInfoRow(infoGrid, 5, "Mô tả:", movie.getDescription());

        infoBox.getChildren().addAll(titleRow, new Separator(), infoGrid);

        mainContent.getChildren().addAll(posterBox, infoBox);
        card.getChildren().add(mainContent);

        return card;
    }

    private String getMovieGenres(UUID movieId) {
        List<String> genres = new ArrayList<>();
        String sql = "SELECT g.name FROM api_genre g " +
                "JOIN api_moviegenre mg ON g.id = mg.genre_id " +
                "WHERE mg.movie_id = ? ORDER BY g.name";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genres.add(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return genres.isEmpty() ? "N/A" : String.join(", ", genres);
    }

    private List<UUID> getMovieGenreIds(UUID movieId) {
        List<UUID> genreIds = new ArrayList<>();
        String sql = "SELECT genre_id FROM api_moviegenre WHERE movie_id = ?";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    genreIds.add(UUID.fromString(rs.getString("genre_id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return genreIds;
    }

    private void saveMovieGenres(UUID movieId, List<UUID> genreIds) {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            // Delete existing genres
            String deleteSql = "DELETE FROM api_moviegenre WHERE movie_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteSql)) {
                ps.setObject(1, movieId);
                ps.executeUpdate();
            }

            // Insert new genres
            if (!genreIds.isEmpty()) {
                String insertSql = "INSERT INTO api_moviegenre (movie_id, genre_id) VALUES (?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    for (UUID genreId : genreIds) {
                        ps.setObject(1, movieId);
                        ps.setObject(2, genreId);
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addInfoRow(GridPane grid, int row, String label, String value) {
        Label labelNode = new Label(label);
        labelNode.setStyle("-fx-font-weight: bold; -fx-text-fill: #7f8c8d;");

        Label valueNode = new Label(value != null ? value : "N/A");
        valueNode.setStyle("-fx-text-fill: #2c3e50;");
        valueNode.setWrapText(true);
        valueNode.setMaxWidth(600);

        grid.add(labelNode, 0, row);
        grid.add(valueNode, 1, row);
    }

    @FXML
    private void handleSearchMovie() {
        String searchTerm = movieSearchField.getText().toLowerCase().trim();

        if (searchTerm.isEmpty()) {
            displayMovies(allMovies);
            return;
        }

        List<Movie> filtered = allMovies.stream()
                .filter(m -> {
                    // Search by title
                    if (m.getTitle().toLowerCase().contains(searchTerm))
                        return true;
                    // Search by director
                    if (m.getDirector() != null && m.getDirector().toLowerCase().contains(searchTerm))
                        return true;
                    // Search by genres
                    String movieGenres = getMovieGenres(m.getId()).toLowerCase();
                    return movieGenres.contains(searchTerm);
                })
                .collect(java.util.stream.Collectors.toList());

        displayMovies(filtered);
    }

    @FXML
    private void handleAddMovie() {
        Dialog<Movie> dialog = new Dialog<>();
        dialog.setTitle("Thêm Phim Mới");
        dialog.setHeaderText("Nhập thông tin phim");

        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField titleField = new TextField();
        titleField.setPromptText("Tên phim");

        // Genre selection with checkboxes
        VBox genreBox = new VBox(5);
        List<CheckBox> genreCheckboxes = new ArrayList<>();
        for (Genre genre : allGenres) {
            CheckBox cb = new CheckBox(genre.getName());
            cb.setUserData(genre.getId());
            genreCheckboxes.add(cb);
            genreBox.getChildren().add(cb);
        }
        ScrollPane genreScrollPane = new ScrollPane(genreBox);
        genreScrollPane.setPrefHeight(150);
        genreScrollPane.setFitToWidth(true);

        TextField directorField = new TextField();
        directorField.setPromptText("Đạo diễn");

        TextField durationField = new TextField();
        durationField.setPromptText("Thời lượng (phút)");

        TextField ratingField = new TextField();
        ratingField.setPromptText("Rating (PG-13, R, etc.)");

        DatePicker releaseDatePicker = new DatePicker();
        releaseDatePicker.setPromptText("Ngày phát hành");

        TextField posterUrlField = new TextField();
        posterUrlField.setPromptText("URL poster (http://... hoặc https://...)");

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Mô tả");
        descriptionArea.setPrefRowCount(3);

        grid.add(new Label("Tên phim:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Thể loại:"), 0, 1);
        grid.add(genreScrollPane, 1, 1);
        grid.add(new Label("Đạo diễn:"), 0, 2);
        grid.add(directorField, 1, 2);
        grid.add(new Label("Thời lượng:"), 0, 3);
        grid.add(durationField, 1, 3);
        grid.add(new Label("Rating:"), 0, 4);
        grid.add(ratingField, 1, 4);
        grid.add(new Label("Ngày phát hành:"), 0, 5);
        grid.add(releaseDatePicker, 1, 5);
        grid.add(new Label("Poster URL:"), 0, 6);
        grid.add(posterUrlField, 1, 6);
        grid.add(new Label("Mô tả:"), 0, 7);
        grid.add(descriptionArea, 1, 7);
        dialog.getDialogPane().setContent(grid);

        Platform.runLater(titleField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    Movie movie = new Movie();
                    movie.setTitle(titleField.getText());
                    movie.setDirector(directorField.getText());
                    movie.setDuration(Integer.parseInt(durationField.getText()));
                    movie.setRating(ratingField.getText());
                    movie.setReleaseDate(releaseDatePicker.getValue());
                    movie.setPosterUrl(posterUrlField.getText());
                    movie.setDescription(descriptionArea.getText());
                    return movie;
                } catch (NumberFormatException e) {
                    showError("Lỗi", "Thời lượng phải là số!");
                    return null;
                }
            }
            return null;
        });

        Optional<Movie> result = dialog.showAndWait();
        result.ifPresent(movie -> {
            // Get selected genres
            List<UUID> selectedGenreIds = genreCheckboxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> (UUID) cb.getUserData())
                    .collect(java.util.stream.Collectors.toList());

            if (movieService.createMovie(movie)) {
                // Save genres
                saveMovieGenres(movie.getId(), selectedGenreIds);

                showSuccess("Thành công", "Đã thêm phim mới!");
                loadMovies();
                loadStatistics();
            } else {
                showError("Lỗi", "Không thể thêm phim!");
            }
        });
    }

    private void handleEditMovie(Movie movie) {
        Dialog<Movie> dialog = new Dialog<>();
        dialog.setTitle("Sửa Phim");
        dialog.setHeaderText("Chỉnh sửa thông tin phim: " + movie.getTitle());

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField titleField = new TextField(movie.getTitle());

        // Genre selection with checkboxes - pre-select existing genres
        VBox genreBox = new VBox(5);
        List<CheckBox> genreCheckboxes = new ArrayList<>();
        List<UUID> currentGenreIds = getMovieGenreIds(movie.getId());

        for (Genre genre : allGenres) {
            CheckBox cb = new CheckBox(genre.getName());
            cb.setUserData(genre.getId());
            cb.setSelected(currentGenreIds.contains(genre.getId()));
            genreCheckboxes.add(cb);
            genreBox.getChildren().add(cb);
        }
        ScrollPane genreScrollPane = new ScrollPane(genreBox);
        genreScrollPane.setPrefHeight(150);
        genreScrollPane.setFitToWidth(true);

        TextField directorField = new TextField(movie.getDirector());
        TextField durationField = new TextField(String.valueOf(movie.getDuration()));
        TextField ratingField = new TextField(movie.getRating() != null ? movie.getRating() : "");
        DatePicker releaseDatePicker = new DatePicker(movie.getReleaseDate());
        TextField posterUrlField = new TextField(movie.getPosterUrl() != null ? movie.getPosterUrl() : "");
        TextArea descriptionArea = new TextArea(movie.getDescription());
        descriptionArea.setPrefRowCount(3);

        grid.add(new Label("Tên phim:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Thể loại:"), 0, 1);
        grid.add(genreScrollPane, 1, 1);
        grid.add(new Label("Đạo diễn:"), 0, 2);
        grid.add(directorField, 1, 2);
        grid.add(new Label("Thời lượng:"), 0, 3);
        grid.add(durationField, 1, 3);
        grid.add(new Label("Rating:"), 0, 4);
        grid.add(ratingField, 1, 4);
        grid.add(new Label("Ngày phát hành:"), 0, 5);
        grid.add(releaseDatePicker, 1, 5);
        grid.add(new Label("Poster URL:"), 0, 6);
        grid.add(posterUrlField, 1, 6);
        grid.add(new Label("Mô tả:"), 0, 7);
        grid.add(descriptionArea, 1, 7);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    movie.setTitle(titleField.getText());
                    movie.setDirector(directorField.getText());
                    movie.setDuration(Integer.parseInt(durationField.getText()));
                    movie.setRating(ratingField.getText());
                    movie.setReleaseDate(releaseDatePicker.getValue());
                    movie.setPosterUrl(posterUrlField.getText());
                    movie.setDescription(descriptionArea.getText());
                    return movie;
                } catch (NumberFormatException e) {
                    showError("Lỗi", "Thời lượng phải là số!");
                    return null;
                }
            }
            return null;
        });

        Optional<Movie> result = dialog.showAndWait();
        result.ifPresent(updatedMovie -> {
            // Get selected genres
            List<UUID> selectedGenreIds = genreCheckboxes.stream()
                    .filter(CheckBox::isSelected)
                    .map(cb -> (UUID) cb.getUserData())
                    .collect(java.util.stream.Collectors.toList());

            if (movieService.updateMovie(updatedMovie)) {
                // Update genres
                saveMovieGenres(updatedMovie.getId(), selectedGenreIds);

                showSuccess("Thành công", "Đã cập nhật phim!");
                loadMovies();
            } else {
                showError("Lỗi", "Không thể cập nhật phim!");
            }
        });
    }

    private void handleDeleteMovie(Movie movie) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc muốn xóa phim này?");
        alert.setContentText(movie.getTitle() + "\n\nLưu ý: Sẽ xóa tất cả suất chiếu và vé liên quan!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (movieService.deleteMovie(movie.getId())) {
                showSuccess("Thành công", "Đã xóa phim!");
                loadMovies();
                loadStatistics();
            } else {
                showError("Lỗi", "Không thể xóa phim!");
            }
        }
    }

    // ==================== USER MANAGEMENT ====================

    private void loadUsers() {
        allUsers = getAllUsers();
        displayUsers(allUsers);
    }

    private List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, email, is_staff, is_active, date_joined FROM api_user ORDER BY date_joined DESC";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(UUID.fromString(rs.getString("id")));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setStaff(rs.getBoolean("is_staff"));
                user.setActive(rs.getBoolean("is_active"));
                user.setDateJoined(rs.getTimestamp("date_joined").toLocalDateTime());
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private void displayUsers(List<User> users) {
        userListContainer.getChildren().clear();

        if (users.isEmpty()) {
            Label emptyLabel = new Label("Không tìm thấy user nào");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
            userListContainer.getChildren().add(emptyLabel);
            return;
        }

        for (User user : users) {
            VBox userCard = createUserCard(user);
            userListContainer.getChildren().add(userCard);
        }
    }

    private VBox createUserCard(User user) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        // Title Row
        HBox titleRow = new HBox(15);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        String icon = user.isStaff() ? "👑" : "👤";
        Label nameLabel = new Label(icon + " " + user.getUsername());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label statusLabel = new Label(user.isActive() ? "✅ Active" : "❌ Inactive");
        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: " + (user.isActive() ? "#27ae60" : "#e74c3c") + "; " +
                "-fx-background-color: " + (user.isActive() ? "#d5f4e6" : "#fadbd8") + "; " +
                "-fx-padding: 5 10; -fx-background-radius: 15;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Action Buttons
        Button editBtn = new Button("✏️ Sửa");
        editBtn.setStyle(
                "-fx-background-color: #f39c12; -fx-text-fill: white; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;");
        editBtn.setOnAction(e -> handleEditUser(user));

        Button deleteBtn = new Button("🗑️ Xóa");
        deleteBtn.setStyle(
                "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;");
        deleteBtn.setOnAction(e -> handleDeleteUser(user));

        titleRow.getChildren().addAll(nameLabel, statusLabel, spacer, editBtn, deleteBtn);

        // Info Grid
        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(20);
        infoGrid.setVgap(8);

        addInfoRow(infoGrid, 0, "Email:", user.getEmail());
        addInfoRow(infoGrid, 1, "Quyền:", user.isStaff() ? "Admin" : "User");
        addInfoRow(infoGrid, 2, "Ngày tạo:", user.getDateJoined() != null ? user.getDateJoined().toString() : "N/A");

        card.getChildren().addAll(titleRow, new Separator(), infoGrid);
        return card;
    }

    @FXML
    private void handleSearchUser() {
        String searchTerm = userSearchField.getText().toLowerCase().trim();

        if (searchTerm.isEmpty()) {
            displayUsers(allUsers);
            return;
        }

        List<User> filtered = allUsers.stream()
                .filter(u -> u.getUsername().toLowerCase().contains(searchTerm) ||
                        (u.getEmail() != null && u.getEmail().toLowerCase().contains(searchTerm)))
                .toList();

        displayUsers(filtered);
    }

    @FXML
    private void handleAddUser() {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Thêm User Mới");
        dialog.setHeaderText("Nhập thông tin user");

        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        CheckBox isStaffCheck = new CheckBox("Quyền Admin");
        CheckBox isActiveCheck = new CheckBox("Tài khoản Active");
        isActiveCheck.setSelected(true);

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(isStaffCheck, 1, 3);
        grid.add(isActiveCheck, 1, 4);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(usernameField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                User user = new User();
                user.setUsername(usernameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(passwordField.getText());
                user.setStaff(isStaffCheck.isSelected());
                user.setActive(isActiveCheck.isSelected());
                return user;
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(user -> {
            if (createUser(user)) {
                showSuccess("Thành công", "Đã thêm user mới!");
                loadUsers();
                loadStatistics();
            } else {
                showError("Lỗi", "Không thể thêm user!");
            }
        });
    }

    private void handleEditUser(User user) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Sửa User");
        dialog.setHeaderText("Chỉnh sửa thông tin user: " + user.getUsername());

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField emailField = new TextField(user.getEmail());
        CheckBox isStaffCheck = new CheckBox("Quyền Admin");
        isStaffCheck.setSelected(user.isStaff());
        CheckBox isActiveCheck = new CheckBox("Tài khoản Active");
        isActiveCheck.setSelected(user.isActive());
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Để trống nếu không đổi password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(new Label(user.getUsername()), 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Password mới:"), 0, 2);
        grid.add(newPasswordField, 1, 2);
        grid.add(isStaffCheck, 1, 3);
        grid.add(isActiveCheck, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                user.setEmail(emailField.getText());
                user.setStaff(isStaffCheck.isSelected());
                user.setActive(isActiveCheck.isSelected());
                if (!newPasswordField.getText().trim().isEmpty()) {
                    user.setPassword(newPasswordField.getText());
                }
                return user;
            }
            return null;
        });

        Optional<User> result = dialog.showAndWait();
        result.ifPresent(updatedUser -> {
            if (updateUser(updatedUser)) {
                showSuccess("Thành công", "Đã cập nhật user!");
                loadUsers();
            } else {
                showError("Lỗi", "Không thể cập nhật user!");
            }
        });
    }

    private void handleDeleteUser(User user) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xóa");
        alert.setHeaderText("Bạn có chắc muốn xóa user này?");
        alert.setContentText(
                user.getUsername() + " (" + user.getEmail() + ")\n\nLưu ý: Sẽ xóa tất cả booking liên quan!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (deleteUser(user.getId())) {
                showSuccess("Thành công", "Đã xóa user!");
                loadUsers();
                loadStatistics();
            } else {
                showError("Lỗi", "Không thể xóa user!");
            }
        }
    }

    private boolean createUser(User user) {
        String sql = "INSERT INTO api_user (id, username, email, password, is_staff, is_active, date_joined) " +
                "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, UUID.randomUUID());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());

            // Hash password using DjangoPassword
            com.ptit.ticketing.auth.DjangoPassword djangoPassword = new com.ptit.ticketing.auth.DjangoPassword();
            String hashedPassword = djangoPassword.hashPassword(user.getPassword());
            ps.setString(4, hashedPassword);

            ps.setBoolean(5, user.isStaff());
            ps.setBoolean(6, user.isActive());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean updateUser(User user) {
        String sql;
        boolean updatePassword = user.getPassword() != null && !user.getPassword().trim().isEmpty();

        if (updatePassword) {
            sql = "UPDATE api_user SET email = ?, password = ?, is_staff = ?, is_active = ? WHERE id = ?";
        } else {
            sql = "UPDATE api_user SET email = ?, is_staff = ?, is_active = ? WHERE id = ?";
        }

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            int paramIndex = 1;
            ps.setString(paramIndex++, user.getEmail());

            if (updatePassword) {
                com.ptit.ticketing.auth.DjangoPassword djangoPassword = new com.ptit.ticketing.auth.DjangoPassword();
                String hashedPassword = djangoPassword.hashPassword(user.getPassword());
                ps.setString(paramIndex++, hashedPassword);
            }

            ps.setBoolean(paramIndex++, user.isStaff());
            ps.setBoolean(paramIndex++, user.isActive());
            ps.setObject(paramIndex, user.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean deleteUser(UUID userId) {
        String sql = "DELETE FROM api_user WHERE id = ?";

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setObject(1, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== STATISTICS ====================

    private void loadStatistics() {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            // Total Movies
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM api_movie");
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalMoviesLabel.setText(String.valueOf(rs.getInt(1)));
                }
            }

            // Total Users
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM api_user");
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalUsersLabel.setText(String.valueOf(rs.getInt(1)));
                }
            }

            // Total Bookings
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM api_booking");
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalBookingsLabel.setText(String.valueOf(rs.getInt(1)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==================== SHOWTIME MANAGEMENT ====================

    private void loadShowtimes() {
        showtimeListContainer.getChildren().clear();

        try {
            // Admin cần thấy TẤT CẢ showtimes (upcoming + ongoing + completed)
            List<com.ptit.ticketing.domain.Showtime> showtimes = showtimeService.getAllShowtimes();

            // Apply filter - SỬ DỤNG TIMEZONE +07:00
            java.time.OffsetDateTime now = java.time.OffsetDateTime.now(java.time.ZoneId.of("Asia/Ho_Chi_Minh"));

            List<com.ptit.ticketing.domain.Showtime> filteredShowtimes = showtimes;

            switch (currentShowtimeFilter) {
                case "upcoming":
                    // Sắp chiếu: Gần thời gian hiện tại nhất lên đầu (sort ASC)
                    filteredShowtimes = showtimes.stream()
                            .filter(st -> now.isBefore(st.getStartTime()))
                            .sorted((a, b) -> a.getStartTime().compareTo(b.getStartTime()))
                            .toList();
                    break;
                case "ongoing":
                    filteredShowtimes = showtimes.stream()
                            .filter(st -> now.isAfter(st.getStartTime()) && now.isBefore(st.getEndTime()))
                            .toList();
                    break;
                case "completed":
                    filteredShowtimes = showtimes.stream()
                            .filter(st -> now.isAfter(st.getEndTime()))
                            .toList();
                    break;
                // "all" - không filter
            }

            if (filteredShowtimes.isEmpty()) {
                String message = switch (currentShowtimeFilter) {
                    case "upcoming" -> "📭 Không có suất chiếu sắp chiếu";
                    case "ongoing" -> "📭 Không có suất chiếu đang chiếu";
                    case "completed" -> "📭 Không có suất chiếu đã kết thúc";
                    default -> "📭 Chưa có suất chiếu nào";
                };
                Label emptyLabel = new Label(message);
                emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d; -fx-padding: 50;");
                showtimeListContainer.getChildren().add(emptyLabel);
                return;
            }

            for (com.ptit.ticketing.domain.Showtime showtime : filteredShowtimes) {
                HBox showtimeCard = createShowtimeCard(showtime);
                showtimeListContainer.getChildren().add(showtimeCard);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải danh sách suất chiếu: " + e.getMessage());
        }
    }

    @FXML
    private void handleFilterAll() {
        setActiveFilter(filterAllBtn, "all");
    }

    @FXML
    private void handleFilterUpcoming() {
        setActiveFilter(filterUpcomingBtn, "upcoming");
    }

    @FXML
    private void handleFilterOngoing() {
        setActiveFilter(filterOngoingBtn, "ongoing");
    }

    @FXML
    private void handleFilterCompleted() {
        setActiveFilter(filterCompletedBtn, "completed");
    }

    private void setActiveFilter(Button activeButton, String filter) {
        // Reset all buttons to inactive style
        String inactiveStyle = "-fx-background-color: #ecf0f1; -fx-text-fill: #34495e; -fx-font-size: 13px; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;";
        String activeStyle = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 13px; -fx-padding: 8 15; -fx-background-radius: 5; -fx-cursor: hand;";

        filterAllBtn.setStyle(inactiveStyle);
        filterUpcomingBtn.setStyle(inactiveStyle);
        filterOngoingBtn.setStyle(inactiveStyle);
        filterCompletedBtn.setStyle(inactiveStyle);

        // Set active button
        activeButton.setStyle(activeStyle);

        // Update filter and reload
        currentShowtimeFilter = filter;
        loadShowtimes();
    }

    private HBox createShowtimeCard(com.ptit.ticketing.domain.Showtime showtime) {
        HBox card = new HBox(15);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-padding: 20; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        Label iconLabel = new Label("🎬");
        iconLabel.setStyle("-fx-font-size: 32px;");

        VBox infoBox = new VBox(5);

        // Movie title với status badge
        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Label movieLabel = new Label(showtime.getMovieTitle());
        movieLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        titleBox.getChildren().add(movieLabel);

        // Xác định trạng thái dựa trên thời gian - SỬ DỤNG TIMEZONE +07:00
        java.time.OffsetDateTime now = java.time.OffsetDateTime.now(java.time.ZoneId.of("Asia/Ho_Chi_Minh"));
        String statusText = "";
        String statusColor = "";
        String statusIcon = "";

        if (now.isBefore(showtime.getStartTime())) {
            // Chưa chiếu
            statusText = "SẮP CHIẾU";
            statusColor = "#3498db"; // Xanh dương
            statusIcon = "⏰";
        } else if (now.isAfter(showtime.getStartTime()) && now.isBefore(showtime.getEndTime())) {
            // Đang chiếu
            statusText = "ĐANG CHIẾU";
            statusColor = "#27ae60"; // Xanh lá
            statusIcon = "▶️";
        } else {
            // Đã kết thúc
            statusText = "ĐÃ KẾT THÚC";
            statusColor = "#95a5a6"; // Xám
            statusIcon = "✅";
        }

        Label statusBadge = new Label(statusIcon + " " + statusText);
        statusBadge.setStyle(
                "-fx-background-color: " + statusColor + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 11px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 4 8; " +
                        "-fx-background-radius: 4;");
        titleBox.getChildren().add(statusBadge);

        Label auditoriumLabel = new Label("🏛️ Phòng: " + showtime.getAuditoriumName());
        auditoriumLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        // Format time properly với timezone +07:00
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedStartTime = showtime.getStartTime().format(formatter);
        String formattedEndTime = showtime.getEndTime().format(formatter);
        Label timeLabel = new Label("🕐 " + formattedStartTime + " - " + formattedEndTime);
        timeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #3498db;");

        infoBox.getChildren().addAll(titleBox, auditoriumLabel, timeLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button editBtn = new Button("✏️ Sửa");
        editBtn.setStyle(
                "-fx-background-color: #f39c12; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        editBtn.setOnAction(e -> handleEditShowtime(showtime));

        Button deleteBtn = new Button("🗑️ Xóa");
        deleteBtn.setStyle(
                "-fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        deleteBtn.setOnAction(e -> handleDeleteShowtime(showtime));

        card.getChildren().addAll(iconLabel, infoBox, spacer, editBtn, deleteBtn);
        return card;
    }

    /**
     * Kiểm tra xem có conflict với suất chiếu khác trong cùng phòng không
     * 
     * @param auditoriumId      ID phòng chiếu
     * @param startTime         Thời gian bắt đầu
     * @param endTime           Thời gian kết thúc
     * @param excludeShowtimeId ID suất chiếu cần loại trừ (dùng khi edit, null khi
     *                          add)
     * @return true nếu có conflict, false nếu không
     */
    private boolean hasShowtimeConflict(UUID auditoriumId, java.time.OffsetDateTime startTime,
            java.time.OffsetDateTime endTime, UUID excludeShowtimeId) {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            // CHỈ check conflict với các suất chiếu CHƯA KẾT THÚC (end_time >= NOW)
            String sql = "SELECT id, start_time, end_time FROM api_showtime " +
                    "WHERE auditorium_id = ? " +
                    "AND status != 'canceled' " +
                    "AND end_time >= NOW()"; // ← CHỈ check suất chiếu chưa kết thúc

            if (excludeShowtimeId != null) {
                sql += " AND id != ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, auditoriumId);
            if (excludeShowtimeId != null) {
                ps.setObject(2, excludeShowtimeId);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                java.time.OffsetDateTime existingStart = rs.getObject("start_time", java.time.OffsetDateTime.class);
                java.time.OffsetDateTime existingEnd = rs.getObject("end_time", java.time.OffsetDateTime.class);

                // Kiểm tra overlap:
                // Có conflict nếu:
                // 1. Start time mới nằm giữa suất chiếu đang có
                // 2. End time mới nằm giữa suất chiếu đang có
                // 3. Suất chiếu mới bao phủ suất chiếu đang có
                boolean overlap = (startTime.isBefore(existingEnd) && endTime.isAfter(existingStart));

                if (overlap) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return true; // Nếu có lỗi, coi như có conflict để an toàn
        }
    }

    @FXML
    private void handleAddShowtime() {
        Dialog<com.ptit.ticketing.domain.Showtime> dialog = new Dialog<>();
        dialog.setTitle("Thêm Suất chiếu Mới");
        dialog.setHeaderText("Nhập thông tin suất chiếu");

        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<com.ptit.ticketing.domain.Movie> movieCombo = new ComboBox<>();
        ComboBox<com.ptit.ticketing.domain.Auditorium> auditoriumCombo = new ComboBox<>();

        // Date & Time Pickers thay vì TextField
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(java.time.LocalDate.now());

        Spinner<Integer> startHourSpinner = new Spinner<>(0, 23, 19);
        Spinner<Integer> startMinuteSpinner = new Spinner<>(0, 59, 0);
        startHourSpinner.setEditable(true);
        startMinuteSpinner.setEditable(true);
        startHourSpinner.setPrefWidth(70);
        startMinuteSpinner.setPrefWidth(70);

        HBox startTimeBox = new HBox(5);
        startTimeBox.getChildren().addAll(
                new Label("Giờ:"), startHourSpinner,
                new Label("Phút:"), startMinuteSpinner);

        TextField basePriceField = new TextField("50000");
        basePriceField.setPromptText("50000");

        // Load movies và auditoriums
        try {
            List<com.ptit.ticketing.domain.Movie> movies = showtimeService.getAllMovies();
            movieCombo.getItems().addAll(movies);
            movieCombo.setConverter(new javafx.util.StringConverter<com.ptit.ticketing.domain.Movie>() {
                @Override
                public String toString(com.ptit.ticketing.domain.Movie movie) {
                    return movie != null ? movie.getTitle() : "";
                }

                @Override
                public com.ptit.ticketing.domain.Movie fromString(String string) {
                    return null;
                }
            });

            List<com.ptit.ticketing.domain.Auditorium> auditoriums = showtimeService.getAllAuditoriums();
            auditoriumCombo.getItems().addAll(auditoriums);
            auditoriumCombo.setConverter(new javafx.util.StringConverter<com.ptit.ticketing.domain.Auditorium>() {
                @Override
                public String toString(com.ptit.ticketing.domain.Auditorium aud) {
                    return aud != null ? aud.getName() : "";
                }

                @Override
                public com.ptit.ticketing.domain.Auditorium fromString(String string) {
                    return null;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        grid.add(new Label("Phim:"), 0, 0);
        grid.add(movieCombo, 1, 0);
        grid.add(new Label("Ngày chiếu:"), 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(new Label("Giờ bắt đầu:"), 0, 2);
        grid.add(startTimeBox, 1, 2);
        grid.add(new Label("Phòng:"), 0, 3);
        grid.add(auditoriumCombo, 1, 3);
        grid.add(new Label("Giá cơ bản:"), 0, 4);
        grid.add(basePriceField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    // Validate inputs
                    if (movieCombo.getValue() == null || auditoriumCombo.getValue() == null) {
                        showAlert("Error", "Vui lòng chọn phim và phòng chiếu!");
                        return null;
                    }

                    // Lấy duration TỪ PHIM (không cần nhập thủ công)
                    com.ptit.ticketing.domain.Movie selectedMovie = movieCombo.getValue();
                    int duration = selectedMovie.getDurationMin();

                    // Tạo start time và end time
                    java.time.LocalDate date = datePicker.getValue();
                    int startHour = startHourSpinner.getValue();
                    int startMinute = startMinuteSpinner.getValue();

                    java.time.LocalDateTime startDateTime = java.time.LocalDateTime.of(
                            date.getYear(), date.getMonth(), date.getDayOfMonth(),
                            startHour, startMinute);

                    java.time.OffsetDateTime startTime = java.time.OffsetDateTime.of(
                            startDateTime, java.time.ZoneOffset.of("+07:00"));

                    // End time = Start time + Duration phim
                    java.time.OffsetDateTime endTime = startTime.plusMinutes(duration);

                    // Kiểm tra conflict với các suất chiếu khác trong cùng phòng
                    UUID auditoriumId = auditoriumCombo.getValue().getId();
                    if (hasShowtimeConflict(auditoriumId, startTime, endTime, null)) {
                        showAlert("Xung đột lịch chiếu",
                                "⚠️ Phòng chiếu này đã có suất chiếu khác trong khoảng thời gian này!\n\n" +
                                        "Vui lòng chọn phòng khác hoặc thời gian khác.");
                        return null;
                    }

                    com.ptit.ticketing.domain.Showtime showtime = new com.ptit.ticketing.domain.Showtime();
                    showtime.setMovieId(selectedMovie.getId());
                    showtime.setAuditoriumId(auditoriumId);
                    showtime.setStartTime(startTime);
                    showtime.setEndTime(endTime);
                    showtime.setBasePrice(new java.math.BigDecimal(basePriceField.getText()));
                    showtime.setStatus("scheduled");
                    return showtime;
                } catch (Exception e) {
                    showAlert("Error", "Dữ liệu không hợp lệ: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        Optional<com.ptit.ticketing.domain.Showtime> result = dialog.showAndWait();
        result.ifPresent(showtime -> {
            try {
                showtimeService.createShowtime(showtime);
                showAlert("Success", "✅ Đã thêm suất chiếu!");
                loadShowtimes();
                loadStatistics();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Không thể thêm suất chiếu: " + e.getMessage());
            }
        });
    }

    private void handleDeleteShowtime(com.ptit.ticketing.domain.Showtime showtime) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận Xóa");
        confirmAlert.setHeaderText("Xóa suất chiếu?");
        confirmAlert.setContentText(
                "Bạn có chắc muốn xóa suất chiếu này?\n\n" +
                        "Phim: " + showtime.getMovieTitle() + "\n" +
                        "Thời gian: " + showtime.getStartTime());

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    showtimeService.deleteShowtime(showtime.getId());
                    showAlert("Success", "✅ Đã xóa suất chiếu!");
                    loadShowtimes();
                    loadStatistics();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Không thể xóa suất chiếu: " + e.getMessage());
                }
            }
        });
    }

    private void handleEditShowtime(com.ptit.ticketing.domain.Showtime showtime) {
        Dialog<com.ptit.ticketing.domain.Showtime> dialog = new Dialog<>();
        dialog.setTitle("Sửa Suất chiếu");
        dialog.setHeaderText("Chỉnh sửa thông tin suất chiếu");

        ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<com.ptit.ticketing.domain.Movie> movieCombo = new ComboBox<>();
        ComboBox<com.ptit.ticketing.domain.Auditorium> auditoriumCombo = new ComboBox<>();

        // Date & Time Pickers
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(showtime.getStartTime().toLocalDate());

        Spinner<Integer> startHourSpinner = new Spinner<>(0, 23, showtime.getStartTime().getHour());
        Spinner<Integer> startMinuteSpinner = new Spinner<>(0, 59, showtime.getStartTime().getMinute());
        startHourSpinner.setEditable(true);
        startMinuteSpinner.setEditable(true);
        startHourSpinner.setPrefWidth(70);
        startMinuteSpinner.setPrefWidth(70);

        HBox startTimeBox = new HBox(5);
        startTimeBox.getChildren().addAll(
                new Label("Giờ:"), startHourSpinner,
                new Label("Phút:"), startMinuteSpinner);

        TextField basePriceField = new TextField();
        basePriceField.setText(showtime.getBasePrice().toString());

        ComboBox<String> statusCombo = new ComboBox<>();
        statusCombo.getItems().addAll("scheduled", "ongoing", "completed", "canceled");
        statusCombo.setValue(showtime.getStatus());

        // Load movies và auditoriums
        try {
            List<com.ptit.ticketing.domain.Movie> movies = showtimeService.getAllMovies();
            movieCombo.getItems().addAll(movies);
            movieCombo.setConverter(new javafx.util.StringConverter<com.ptit.ticketing.domain.Movie>() {
                @Override
                public String toString(com.ptit.ticketing.domain.Movie movie) {
                    return movie != null ? movie.getTitle() : "";
                }

                @Override
                public com.ptit.ticketing.domain.Movie fromString(String string) {
                    return null;
                }
            });

            List<com.ptit.ticketing.domain.Auditorium> auditoriums = showtimeService.getAllAuditoriums();
            auditoriumCombo.getItems().addAll(auditoriums);
            auditoriumCombo.setConverter(new javafx.util.StringConverter<com.ptit.ticketing.domain.Auditorium>() {
                @Override
                public String toString(com.ptit.ticketing.domain.Auditorium aud) {
                    return aud != null ? aud.getName() : "";
                }

                @Override
                public com.ptit.ticketing.domain.Auditorium fromString(String string) {
                    return null;
                }
            });

            // Pre-fill with existing showtime data
            for (com.ptit.ticketing.domain.Movie movie : movies) {
                if (movie.getId().equals(showtime.getMovieId())) {
                    movieCombo.setValue(movie);
                    break;
                }
            }

            for (com.ptit.ticketing.domain.Auditorium aud : auditoriums) {
                if (aud.getId().equals(showtime.getAuditoriumId())) {
                    auditoriumCombo.setValue(aud);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        grid.add(new Label("Phim:"), 0, 0);
        grid.add(movieCombo, 1, 0);
        grid.add(new Label("Ngày chiếu:"), 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(new Label("Giờ bắt đầu:"), 0, 2);
        grid.add(startTimeBox, 1, 2);
        grid.add(new Label("Phòng:"), 0, 3);
        grid.add(auditoriumCombo, 1, 3);
        grid.add(new Label("Giá cơ bản:"), 0, 4);
        grid.add(basePriceField, 1, 4);
        grid.add(new Label("Trạng thái:"), 0, 5);
        grid.add(statusCombo, 1, 5);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    // Validate
                    if (movieCombo.getValue() == null || auditoriumCombo.getValue() == null) {
                        showAlert("Error", "Vui lòng chọn phim và phòng chiếu!");
                        return null;
                    }

                    // Lấy duration TỪ PHIM (không cần nhập thủ công)
                    com.ptit.ticketing.domain.Movie selectedMovie = movieCombo.getValue();
                    int duration = selectedMovie.getDurationMin();

                    // Tạo start time và end time mới
                    java.time.LocalDate date = datePicker.getValue();
                    int startHour = startHourSpinner.getValue();
                    int startMinute = startMinuteSpinner.getValue();

                    java.time.LocalDateTime startDateTime = java.time.LocalDateTime.of(
                            date.getYear(), date.getMonth(), date.getDayOfMonth(),
                            startHour, startMinute);

                    java.time.OffsetDateTime newStartTime = java.time.OffsetDateTime.of(
                            startDateTime, java.time.ZoneOffset.of("+07:00"));

                    // End time = Start time + Duration phim
                    java.time.OffsetDateTime newEndTime = newStartTime.plusMinutes(duration);

                    // Kiểm tra conflict (loại trừ chính nó khi check)
                    UUID auditoriumId = auditoriumCombo.getValue().getId();
                    if (hasShowtimeConflict(auditoriumId, newStartTime, newEndTime, showtime.getId())) {
                        showAlert("Xung đột lịch chiếu",
                                "⚠️ Phòng chiếu này đã có suất chiếu khác trong khoảng thời gian này!\n\n" +
                                        "Vui lòng chọn phòng khác hoặc thời gian khác.");
                        return null;
                    }

                    showtime.setMovieId(selectedMovie.getId());
                    showtime.setAuditoriumId(auditoriumId);
                    showtime.setStartTime(newStartTime);
                    showtime.setEndTime(newEndTime);
                    showtime.setBasePrice(new java.math.BigDecimal(basePriceField.getText()));
                    showtime.setStatus(statusCombo.getValue());
                    return showtime;
                } catch (Exception e) {
                    showAlert("Error", "Dữ liệu không hợp lệ: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        Optional<com.ptit.ticketing.domain.Showtime> result = dialog.showAndWait();
        result.ifPresent(updatedShowtime -> {
            try {
                showtimeService.updateShowtime(updatedShowtime);
                showAlert("Success", "✅ Đã cập nhật suất chiếu!");
                loadShowtimes();
                loadStatistics();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Không thể cập nhật suất chiếu: " + e.getMessage());
            }
        });
    }

    // ==================== AUDITORIUM MANAGEMENT ====================

    private void loadAuditoriums() {
        auditoriumListContainer.getChildren().clear();

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            String sql = "SELECT * FROM api_auditorium ORDER BY name";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean hasAuditoriums = false;
            while (rs.next()) {
                hasAuditoriums = true;
                HBox audCard = createAuditoriumCard(rs);
                auditoriumListContainer.getChildren().add(audCard);
            }

            if (!hasAuditoriums) {
                Label emptyLabel = new Label("📭 Chưa có phòng chiếu nào");
                emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d; -fx-padding: 50;");
                auditoriumListContainer.getChildren().add(emptyLabel);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải danh sách phòng chiếu: " + e.getMessage());
        }
    }

    private HBox createAuditoriumCard(ResultSet rs) throws Exception {
        HBox card = new HBox(15);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-padding: 20; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        Label iconLabel = new Label("🏛️");
        iconLabel.setStyle("-fx-font-size: 32px;");

        VBox infoBox = new VBox(5);
        Label nameLabel = new Label(rs.getString("name"));
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label capacityLabel = new Label(
                "💺 " + rs.getInt("seats_per_row") + " ghế/hàng | " +
                        "Standard: " + rs.getInt("standard_row_count") + " hàng | " +
                        "VIP: " + rs.getInt("vip_row_count") + " hàng | " +
                        "Couple: " + rs.getInt("couple_row_count") + " hàng");
        capacityLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        infoBox.getChildren().addAll(nameLabel, capacityLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Buttons
        HBox buttonsBox = new HBox(10);
        Button editBtn = new Button("✏️ Sửa");
        editBtn.setStyle(
                "-fx-background-color: #3498db; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        UUID auditoriumId = (UUID) rs.getObject("id");
        editBtn.setOnAction(e -> handleEditAuditorium(auditoriumId));

        Button deleteBtn = new Button("🗑️ Xóa");
        deleteBtn.setStyle(
                "-fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        String auditoriumName = rs.getString("name");
        deleteBtn.setOnAction(e -> handleDeleteAuditorium(auditoriumId, auditoriumName));

        buttonsBox.getChildren().addAll(editBtn, deleteBtn);

        card.getChildren().addAll(iconLabel, infoBox, spacer, buttonsBox);
        return card;
    }

    @FXML
    private void handleAddAuditorium() {
        Dialog<com.ptit.ticketing.domain.Auditorium> dialog = new Dialog<>();
        dialog.setTitle("Thêm Phòng Chiếu Mới");
        dialog.setHeaderText("Nhập thông tin phòng chiếu");

        ButtonType addButtonType = new ButtonType("Thêm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        TextField standardRowsField = new TextField();
        TextField vipRowsField = new TextField();
        TextField coupleRowsField = new TextField();
        TextField seatsPerRowField = new TextField();

        nameField.setPromptText("Phòng 1");
        standardRowsField.setPromptText("5");
        vipRowsField.setPromptText("3");
        coupleRowsField.setPromptText("2");
        seatsPerRowField.setPromptText("10");

        grid.add(new Label("Tên phòng:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Số hàng Standard:"), 0, 1);
        grid.add(standardRowsField, 1, 1);
        grid.add(new Label("Số hàng VIP:"), 0, 2);
        grid.add(vipRowsField, 1, 2);
        grid.add(new Label("Số hàng Couple:"), 0, 3);
        grid.add(coupleRowsField, 1, 3);
        grid.add(new Label("Số ghế/hàng:"), 0, 4);
        grid.add(seatsPerRowField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    com.ptit.ticketing.domain.Auditorium aud = new com.ptit.ticketing.domain.Auditorium();
                    aud.setName(nameField.getText());
                    aud.setStandardRowCount(Integer.parseInt(standardRowsField.getText()));
                    aud.setVipRowCount(Integer.parseInt(vipRowsField.getText()));
                    aud.setCoupleRowCount(Integer.parseInt(coupleRowsField.getText()));
                    aud.setSeatsPerRow(Integer.parseInt(seatsPerRowField.getText()));
                    return aud;
                } catch (Exception e) {
                    showAlert("Error", "Dữ liệu không hợp lệ: " + e.getMessage());
                    return null;
                }
            }
            return null;
        });

        Optional<com.ptit.ticketing.domain.Auditorium> result = dialog.showAndWait();
        result.ifPresent(aud -> {
            try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
                conn.setAutoCommit(false); // Start transaction

                try {
                    // Validate: seats_per_row must be even for couple rows
                    if (aud.getCoupleRowCount() > 0 && aud.getSeatsPerRow() % 2 != 0) {
                        showAlert("Error", "⚠️ Số ghế/hàng phải là số chẵn khi có hàng ghế đôi!");
                        conn.rollback();
                        return;
                    }

                    // Insert auditorium
                    UUID auditoriumId = UUID.randomUUID();
                    String sqlAud = "INSERT INTO api_auditorium (id, name, standard_row_count, vip_row_count, couple_row_count, seats_per_row, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";
                    PreparedStatement psAud = conn.prepareStatement(sqlAud);
                    psAud.setObject(1, auditoriumId);
                    psAud.setString(2, aud.getName());
                    psAud.setInt(3, aud.getStandardRowCount());
                    psAud.setInt(4, aud.getVipRowCount());
                    psAud.setInt(5, aud.getCoupleRowCount());
                    psAud.setInt(6, aud.getSeatsPerRow());
                    psAud.executeUpdate();

                    // Auto-generate seats
                    String sqlSeat = "INSERT INTO api_seat (id, row_label, seat_number, seat_type, auditorium_id) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psSeat = conn.prepareStatement(sqlSeat);

                    // Standard seats (rows A, B, C...)
                    for (int i = 0; i < aud.getStandardRowCount(); i++) {
                        String rowLabel = String.valueOf((char) ('A' + i));
                        for (int seatNum = 1; seatNum <= aud.getSeatsPerRow(); seatNum++) {
                            psSeat.setObject(1, UUID.randomUUID());
                            psSeat.setString(2, rowLabel);
                            psSeat.setInt(3, seatNum);
                            psSeat.setString(4, "standard");
                            psSeat.setObject(5, auditoriumId);
                            psSeat.executeUpdate();
                        }
                    }

                    // VIP seats
                    for (int i = 0; i < aud.getVipRowCount(); i++) {
                        String rowLabel = String.valueOf((char) ('A' + aud.getStandardRowCount() + i));
                        for (int seatNum = 1; seatNum <= aud.getSeatsPerRow(); seatNum++) {
                            psSeat.setObject(1, UUID.randomUUID());
                            psSeat.setString(2, rowLabel);
                            psSeat.setInt(3, seatNum);
                            psSeat.setString(4, "vip");
                            psSeat.setObject(5, auditoriumId);
                            psSeat.executeUpdate();
                        }
                    }

                    // Couple seats - CHỈ TẠO SEATS_PER_ROW / 2 ghế (mỗi ghế là 1 cặp đôi)
                    int coupleSeatCount = aud.getSeatsPerRow() / 2;
                    for (int i = 0; i < aud.getCoupleRowCount(); i++) {
                        String rowLabel = String
                                .valueOf((char) ('A' + aud.getStandardRowCount() + aud.getVipRowCount() + i));
                        for (int seatNum = 1; seatNum <= coupleSeatCount; seatNum++) {
                            psSeat.setObject(1, UUID.randomUUID());
                            psSeat.setString(2, rowLabel);
                            psSeat.setInt(3, seatNum);
                            psSeat.setString(4, "couple");
                            psSeat.setObject(5, auditoriumId);
                            psSeat.executeUpdate();
                        }
                    }

                    conn.commit(); // Commit transaction

                    int totalSeats = (aud.getStandardRowCount() * aud.getSeatsPerRow()) +
                            (aud.getVipRowCount() * aud.getSeatsPerRow()) +
                            (aud.getCoupleRowCount() * coupleSeatCount);

                    showAlert("Success", "✅ Đã thêm phòng chiếu!\n\n" +
                            "📍 Tên: " + aud.getName() + "\n" +
                            "🎫 Tổng ghế: " + totalSeats + " ghế\n" +
                            "   • Standard: " + (aud.getStandardRowCount() * aud.getSeatsPerRow()) + " ghế ("
                            + aud.getStandardRowCount() + " hàng x " + aud.getSeatsPerRow() + ")\n" +
                            "   • VIP: " + (aud.getVipRowCount() * aud.getSeatsPerRow()) + " ghế ("
                            + aud.getVipRowCount() + " hàng x " + aud.getSeatsPerRow() + ")\n" +
                            "   • Couple: " + (aud.getCoupleRowCount() * coupleSeatCount) + " ghế đôi ("
                            + aud.getCoupleRowCount() + " hàng x " + coupleSeatCount + " cặp)");
                    loadAuditoriums();

                } catch (Exception e) {
                    conn.rollback(); // Rollback on error
                    throw e;
                }

            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Không thể thêm phòng chiếu: " + e.getMessage());
            }
        });
    }

    private void handleEditAuditorium(UUID auditoriumId) {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            String sql = "SELECT * FROM api_auditorium WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, auditoriumId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                showAlert("Error", "Không tìm thấy phòng chiếu");
                return;
            }

            Dialog<com.ptit.ticketing.domain.Auditorium> dialog = new Dialog<>();
            dialog.setTitle("Sửa Phòng Chiếu");
            dialog.setHeaderText("Cập nhật thông tin phòng chiếu");

            ButtonType saveButtonType = new ButtonType("Lưu", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField nameField = new TextField(rs.getString("name"));
            TextField standardRowsField = new TextField(String.valueOf(rs.getInt("standard_row_count")));
            TextField vipRowsField = new TextField(String.valueOf(rs.getInt("vip_row_count")));
            TextField coupleRowsField = new TextField(String.valueOf(rs.getInt("couple_row_count")));
            TextField seatsPerRowField = new TextField(String.valueOf(rs.getInt("seats_per_row")));

            grid.add(new Label("Tên phòng:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Số hàng Standard:"), 0, 1);
            grid.add(standardRowsField, 1, 1);
            grid.add(new Label("Số hàng VIP:"), 0, 2);
            grid.add(vipRowsField, 1, 2);
            grid.add(new Label("Số hàng Couple:"), 0, 3);
            grid.add(coupleRowsField, 1, 3);
            grid.add(new Label("Số ghế/hàng:"), 0, 4);
            grid.add(seatsPerRowField, 1, 4);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    try {
                        com.ptit.ticketing.domain.Auditorium aud = new com.ptit.ticketing.domain.Auditorium();
                        aud.setId(auditoriumId);
                        aud.setName(nameField.getText());
                        aud.setStandardRowCount(Integer.parseInt(standardRowsField.getText()));
                        aud.setVipRowCount(Integer.parseInt(vipRowsField.getText()));
                        aud.setCoupleRowCount(Integer.parseInt(coupleRowsField.getText()));
                        aud.setSeatsPerRow(Integer.parseInt(seatsPerRowField.getText()));
                        return aud;
                    } catch (Exception e) {
                        showAlert("Error", "Dữ liệu không hợp lệ: " + e.getMessage());
                        return null;
                    }
                }
                return null;
            });

            Optional<com.ptit.ticketing.domain.Auditorium> result = dialog.showAndWait();
            result.ifPresent(aud -> {
                try (Connection updateConn = com.ptit.ticketing.config.Database.get().getConnection()) {
                    String updateSql = "UPDATE api_auditorium SET name = ?, standard_row_count = ?, vip_row_count = ?, couple_row_count = ?, seats_per_row = ?, updated_at = NOW() WHERE id = ?";
                    PreparedStatement updatePs = updateConn.prepareStatement(updateSql);
                    updatePs.setString(1, aud.getName());
                    updatePs.setInt(2, aud.getStandardRowCount());
                    updatePs.setInt(3, aud.getVipRowCount());
                    updatePs.setInt(4, aud.getCoupleRowCount());
                    updatePs.setInt(5, aud.getSeatsPerRow());
                    updatePs.setObject(6, auditoriumId);
                    updatePs.executeUpdate();

                    showAlert("Success", "✅ Đã cập nhật phòng chiếu!");
                    loadAuditoriums();
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Không thể cập nhật phòng chiếu: " + e.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Lỗi: " + e.getMessage());
        }
    }

    private void handleDeleteAuditorium(UUID auditoriumId, String name) {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            // Check if auditorium has any showtimes
            String checkShowtimes = "SELECT COUNT(*) FROM api_showtime WHERE auditorium_id = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkShowtimes);
            psCheck.setObject(1, auditoriumId);
            ResultSet rs = psCheck.executeQuery();
            rs.next();
            int showtimeCount = rs.getInt(1);

            if (showtimeCount > 0) {
                showAlert("Error", "❌ Không thể xóa phòng chiếu!\n\n" +
                        "Phòng chiếu '" + name + "' đang có " + showtimeCount + " suất chiếu.\n\n" +
                        "Vui lòng xóa các suất chiếu trước khi xóa phòng chiếu.");
                return;
            }

            // Check seat count for confirmation message
            String checkSeats = "SELECT COUNT(*) FROM api_seat WHERE auditorium_id = ?";
            PreparedStatement psSeats = conn.prepareStatement(checkSeats);
            psSeats.setObject(1, auditoriumId);
            ResultSet rsSeats = psSeats.executeQuery();
            rsSeats.next();
            int seatCount = rsSeats.getInt(1);

            // Confirm deletion
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Xác nhận Xóa");
            confirmAlert.setHeaderText("Xóa phòng chiếu?");
            confirmAlert.setContentText("Bạn có chắc muốn xóa phòng chiếu: " + name + "?\n\n" +
                    "Sẽ xóa " + seatCount + " ghế cùng với phòng chiếu này.");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        conn.setAutoCommit(false); // Start transaction

                        // Delete all seats first (child records)
                        String deleteSeatsSql = "DELETE FROM api_seat WHERE auditorium_id = ?";
                        PreparedStatement psDelSeats = conn.prepareStatement(deleteSeatsSql);
                        psDelSeats.setObject(1, auditoriumId);
                        int deletedSeats = psDelSeats.executeUpdate();

                        // Delete auditorium (parent record)
                        String deleteAudSql = "DELETE FROM api_auditorium WHERE id = ?";
                        PreparedStatement psDelAud = conn.prepareStatement(deleteAudSql);
                        psDelAud.setObject(1, auditoriumId);
                        psDelAud.executeUpdate();

                        conn.commit(); // Commit transaction

                        showAlert("Success", "✅ Đã xóa phòng chiếu!\n\n" +
                                "Đã xóa: " + deletedSeats + " ghế và phòng chiếu '" + name + "'");
                        loadAuditoriums();

                    } catch (Exception e) {
                        try {
                            conn.rollback(); // Rollback on error
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        e.printStackTrace();
                        showAlert("Error", "Không thể xóa phòng chiếu: " + e.getMessage());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể kiểm tra phòng chiếu: " + e.getMessage());
        }
    }

    // ==================== BOOKING MANAGEMENT ====================

    @FXML
    private void loadBookings() {
        bookingListContainer.getChildren().clear();

        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            String sql = """
                    SELECT b.*, u.username, m.title as movie_title, s.start_time
                    FROM api_booking b
                    JOIN api_user u ON b.user_id = u.id
                    JOIN api_showtime s ON b.showtime_id = s.id
                    JOIN api_movie m ON s.movie_id = m.id
                    ORDER BY b.created_at DESC
                    LIMIT 50
                    """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean hasBookings = false;
            while (rs.next()) {
                hasBookings = true;
                HBox bookingCard = createBookingCard(rs);
                bookingListContainer.getChildren().add(bookingCard);
            }

            if (!hasBookings) {
                Label emptyLabel = new Label("📭 Chưa có booking nào");
                emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d; -fx-padding: 50;");
                bookingListContainer.getChildren().add(emptyLabel);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải danh sách bookings: " + e.getMessage());
        }
    }

    private HBox createBookingCard(ResultSet rs) throws Exception {
        HBox card = new HBox(15);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-padding: 20; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        Label iconLabel = new Label("🎟️");
        iconLabel.setStyle("-fx-font-size: 32px;");

        VBox infoBox = new VBox(5);
        Label movieLabel = new Label("🎬 " + rs.getString("movie_title"));
        movieLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label userLabel = new Label("👤 " + rs.getString("username"));
        userLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        Label amountLabel = new Label(
                "💰 " + String.format("%.0f VND", rs.getBigDecimal("total_amount").doubleValue()));
        amountLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

        String status = rs.getString("status");
        Label statusLabel = new Label("📊 " + status.toUpperCase());
        String statusColor = switch (status) {
            case "paid" -> "#27ae60";
            case "pending_approval" -> "#f39c12";
            case "canceled" -> "#e74c3c";
            default -> "#7f8c8d";
        };
        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: " + statusColor + "; -fx-font-weight: bold;");

        infoBox.getChildren().addAll(movieLabel, userLabel, amountLabel, statusLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Button xem chi tiết vé
        Button viewBtn = new Button("👁️ Xem vé");
        viewBtn.setStyle(
                "-fx-background-color: #3498db; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;");
        UUID bookingId = (UUID) rs.getObject("id");
        viewBtn.setOnAction(e -> handleViewBookingTickets(bookingId));

        card.getChildren().addAll(iconLabel, infoBox, spacer, viewBtn);
        return card;
    }

    private void handleViewBookingTickets(UUID bookingId) {
        try (Connection conn = com.ptit.ticketing.config.Database.get().getConnection()) {
            // Get booking info
            String bookingSql = """
                    SELECT b.*, u.username, m.title as movie_title, s.start_time, a.name as auditorium_name
                    FROM api_booking b
                    JOIN api_user u ON b.user_id = u.id
                    JOIN api_showtime s ON b.showtime_id = s.id
                    JOIN api_movie m ON s.movie_id = m.id
                    JOIN api_auditorium a ON s.auditorium_id = a.id
                    WHERE b.id = ?
                    """;
            PreparedStatement bookingPs = conn.prepareStatement(bookingSql);
            bookingPs.setObject(1, bookingId);
            ResultSet bookingRs = bookingPs.executeQuery();

            if (!bookingRs.next()) {
                showAlert("Error", "Không tìm thấy booking");
                return;
            }

            // Get tickets/seats
            String ticketsSql = """
                    SELECT t.*, se.row_label, se.seat_number, se.seat_type
                    FROM api_ticket t
                    JOIN api_seat se ON t.seat_id = se.id
                    WHERE t.booking_id = ?
                    ORDER BY se.row_label, se.seat_number
                    """;
            PreparedStatement ticketsPs = conn.prepareStatement(ticketsSql);
            ticketsPs.setObject(1, bookingId);
            ResultSet ticketsRs = ticketsPs.executeQuery();

            StringBuilder seatsInfo = new StringBuilder();
            int count = 0;
            while (ticketsRs.next()) {
                count++;
                String seat = ticketsRs.getString("row_label") + ticketsRs.getInt("seat_number");
                String seatType = ticketsRs.getString("seat_type");
                seatsInfo.append(seat).append(" (").append(seatType).append(")\n");
            }

            // Show dialog
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Chi tiết Booking");
            infoAlert.setHeaderText("🎟️ Thông tin Vé");
            infoAlert.setContentText(
                    "Booking ID: " + bookingId + "\n" +
                            "User: " + bookingRs.getString("username") + "\n\n" +
                            "🎬 Phim: " + bookingRs.getString("movie_title") + "\n" +
                            "🏛️ Phòng: " + bookingRs.getString("auditorium_name") + "\n" +
                            "🕐 Giờ chiếu: " + bookingRs.getTimestamp("start_time") + "\n\n" +
                            "💺 Ghế (" + count + " ghế):\n" + seatsInfo.toString() + "\n" +
                            "💰 Tổng tiền: "
                            + String.format("%.0f VND", bookingRs.getBigDecimal("total_amount").doubleValue()) + "\n" +
                            "📊 Trạng thái: " + bookingRs.getString("status").toUpperCase() + "\n" +
                            "💳 Thanh toán: " + bookingRs.getString("payment_method").toUpperCase());
            infoAlert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể xem chi tiết: " + e.getMessage());
        }
    }

    // ==================== PENDING APPROVALS (QR PAYMENT) ====================

    @FXML
    private void loadPendingApprovals() {
        pendingApprovalsContainer.getChildren().clear();

        try {
            List<com.ptit.ticketing.domain.Booking> pendingBookings = bookingService.getPendingApprovals();

            if (pendingBookings.isEmpty()) {
                Label emptyLabel = new Label("📭 Không có yêu cầu thanh toán nào đang chờ");
                emptyLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d; -fx-padding: 50;");
                pendingApprovalsContainer.getChildren().add(emptyLabel);
                return;
            }

            for (com.ptit.ticketing.domain.Booking booking : pendingBookings) {
                VBox bookingCard = createPendingApprovalCard(booking);
                pendingApprovalsContainer.getChildren().add(bookingCard);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Không thể tải danh sách pending approvals: " + e.getMessage());
        }
    }

    private VBox createPendingApprovalCard(com.ptit.ticketing.domain.Booking booking) {
        VBox card = new VBox(10);
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-padding: 20; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        // Header with booking info
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        Label icon = new Label("💳");
        icon.setStyle("-fx-font-size: 32px;");

        VBox infoBox = new VBox(5);
        Label titleLabel = new Label("🎬 " + booking.getMovieTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label userLabel = new Label("👤 User: " + booking.getUserName());
        userLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        // Format datetime properly
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedTime = booking.getShowtimeStart()
                .atZoneSameInstant(ZoneId.of("Asia/Ho_Chi_Minh"))
                .format(formatter);
        Label timeLabel = new Label("🕐 " + formattedTime);
        timeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        Label amountLabel = new Label("💰 " + String.format("%.0f VND", booking.getTotalAmount().doubleValue()));
        amountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        infoBox.getChildren().addAll(titleLabel, userLabel, timeLabel, amountLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Action buttons
        HBox actionBox = new HBox(10);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        Button approveBtn = new Button("✅ Phê duyệt");
        approveBtn.setStyle(
                "-fx-background-color: #27ae60; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand; " +
                        "-fx-font-weight: bold;");
        approveBtn.setOnAction(e -> handleApproveBooking(booking));

        Button rejectBtn = new Button("❌ Từ chối");
        rejectBtn.setStyle(
                "-fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand; " +
                        "-fx-font-weight: bold;");
        rejectBtn.setOnAction(e -> handleRejectBooking(booking));

        actionBox.getChildren().addAll(approveBtn, rejectBtn);

        header.getChildren().addAll(icon, infoBox, spacer, actionBox);
        card.getChildren().add(header);

        return card;
    }

    private void handleApproveBooking(com.ptit.ticketing.domain.Booking booking) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận Phê duyệt");
        confirmAlert.setHeaderText("✅ Phê duyệt Thanh toán QR");
        confirmAlert.setContentText(
                "Bạn có chắc muốn phê duyệt booking này?\n\n" +
                        "User: " + booking.getUserName() + "\n" +
                        "Movie: " + booking.getMovieTitle() + "\n" +
                        "Amount: " + String.format("%.0f VND", booking.getTotalAmount().doubleValue()));

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean success = bookingService.approveBooking(booking.getId());
                    if (success) {
                        showAlert("Success", "✅ Đã phê duyệt booking thành công!");
                        loadPendingApprovals(); // Refresh list
                        loadStatistics(); // Update stats
                    } else {
                        showAlert("Error", "Không thể phê duyệt booking.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Lỗi: " + e.getMessage());
                }
            }
        });
    }

    private void handleRejectBooking(com.ptit.ticketing.domain.Booking booking) {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận Từ chối");
        confirmAlert.setHeaderText("❌ Từ chối Thanh toán QR");
        confirmAlert.setContentText(
                "Bạn có chắc muốn từ chối booking này?\n\n" +
                        "User: " + booking.getUserName() + "\n" +
                        "Movie: " + booking.getMovieTitle() + "\n" +
                        "Amount: " + String.format("%.0f VND", booking.getTotalAmount().doubleValue()));

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean success = bookingService.rejectBooking(booking.getId());
                    if (success) {
                        showAlert("Success", "❌ Đã từ chối booking.");
                        loadPendingApprovals(); // Refresh list
                    } else {
                        showAlert("Error", "Không thể từ chối booking.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "Lỗi: " + e.getMessage());
                }
            }
        });
    }

    // ==================== NAVIGATION ====================

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/dashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.setTitle("Cinema Management - Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            showError("Lỗi", "Không thể quay lại dashboard!");
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
            showError("Lỗi", "Không thể mở Settings!");
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

            Stage stage = (Stage) backButton.getScene().getWindow();
            Scene scene = new Scene(root, 900, 600);
            stage.setScene(scene);
            stage.setTitle("Cinema Management System - Login");
            stage.centerOnScreen();

            System.out.println("✅ Logged out successfully from Admin Panel");

        } catch (IOException e) {
            e.printStackTrace();
            showError("Lỗi", "Không thể logout!");
        }
    }

    // ==================== HELPERS ====================

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ==================== REVENUE REPORT & EXPORT ====================

    @FXML
    private void handleViewRevenue() {
        // Dialog xem thống kê
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Thống kê doanh thu");
        dialog.setHeaderText(null);

        ButtonType closeButtonType = new ButtonType("Đóng", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(closeButtonType);

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));

        // Date pickers
        HBox dateBox = new HBox(10);
        dateBox.setAlignment(Pos.CENTER_LEFT);
        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setValue(java.time.LocalDate.now().minusMonths(1));
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setValue(java.time.LocalDate.now());

        Button refreshButton = new Button("🔄 Làm mới");
        Button exportButton = new Button("📥 Xuất Excel");
        exportButton.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");

        dateBox.getChildren().addAll(
                new Label("Từ:"), fromDatePicker,
                new Label("Đến:"), toDatePicker,
                refreshButton,
                exportButton);

        // Revenue display area
        VBox statsBox = new VBox(10);
        statsBox.setPadding(new Insets(10));
        statsBox.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5;");

        content.getChildren().addAll(dateBox, statsBox);

        // Load initial stats
        refreshButton.setOnAction(e -> {
            try {
                java.time.LocalDate fromDate = fromDatePicker.getValue();
                java.time.LocalDate toDate = toDatePicker.getValue();

                loadRevenueStats(statsBox, fromDate, toDate);
            } catch (Exception ex) {
                showError("Lỗi", "Không thể tải thống kê: " + ex.getMessage());
            }
        });

        // Export button handler
        exportButton.setOnAction(e -> {
            try {
                java.time.LocalDate fromDate = fromDatePicker.getValue();
                java.time.LocalDate toDate = toDatePicker.getValue();

                // Chọn nơi lưu file
                javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
                fileChooser.setTitle("Lưu báo cáo Excel");
                fileChooser.setInitialFileName("BaoCaoDoanhThu_" +
                        java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd")) +
                        ".xlsx");
                fileChooser.getExtensionFilters().add(
                        new javafx.stage.FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

                Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
                java.io.File file = fileChooser.showSaveDialog(stage);

                if (file != null) {
                    // Export to Excel
                    reportService.exportRevenueToExcel(fromDate, toDate, file.getAbsolutePath());
                    showSuccess("Thành công",
                            "✅ Đã xuất báo cáo doanh thu!\n\n" +
                                    "Từ ngày: "
                                    + fromDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n"
                                    +
                                    "Đến ngày: "
                                    + toDate.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n\n"
                                    +
                                    "File: " + file.getAbsolutePath());
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                showError("Lỗi", "Không thể xuất báo cáo: " + ex.getMessage());
            }
        });

        // Auto-load first time
        loadRevenueStats(statsBox, fromDatePicker.getValue(), toDatePicker.getValue());

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);

        dialog.getDialogPane().setContent(scrollPane);
        dialog.getDialogPane().setPrefWidth(700);
        dialog.showAndWait();
    }

    private void loadRevenueStats(VBox container, java.time.LocalDate fromDate, java.time.LocalDate toDate) {
        container.getChildren().clear();

        try {
            // Summary
            Label titleLabel = new Label("📊 TỔNG QUAN DOANH THU");
            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            container.getChildren().add(titleLabel);

            // Get summary data
            java.util.List<com.ptit.ticketing.service.ReportService.DailyRevenue> dailyRevenues = reportService
                    .getDailyRevenue(fromDate, toDate);

            java.math.BigDecimal totalRevenue = dailyRevenues.stream()
                    .map(com.ptit.ticketing.service.ReportService.DailyRevenue::getTotalRevenue)
                    .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

            int totalBookings = dailyRevenues.stream()
                    .mapToInt(com.ptit.ticketing.service.ReportService.DailyRevenue::getTotalBookings)
                    .sum();

            int totalTickets = dailyRevenues.stream()
                    .mapToInt(com.ptit.ticketing.service.ReportService.DailyRevenue::getTotalTickets)
                    .sum();

            VBox summaryBox = new VBox(8);
            summaryBox.setPadding(new Insets(10));
            summaryBox.setStyle(
                    "-fx-background-color: white; -fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

            summaryBox.getChildren().addAll(
                    createStatRow("💰 Tổng doanh thu:", String.format("%,d VNĐ", totalRevenue.longValue())),
                    createStatRow("🎫 Tổng số vé:", String.valueOf(totalTickets)),
                    createStatRow("📦 Tổng booking:", String.valueOf(totalBookings)));

            container.getChildren().add(summaryBox);

            // Movie revenue
            Label movieTitleLabel = new Label("🎬 DOANH THU THEO PHIM");
            movieTitleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 0 5 0;");
            container.getChildren().add(movieTitleLabel);

            java.util.List<com.ptit.ticketing.service.ReportService.MovieRevenue> movieRevenues = reportService
                    .getMovieRevenue(fromDate, toDate);

            if (movieRevenues.isEmpty()) {
                Label emptyLabel = new Label("Không có dữ liệu");
                emptyLabel.setStyle("-fx-text-fill: #7f8c8d;");
                container.getChildren().add(emptyLabel);
            } else {
                for (com.ptit.ticketing.service.ReportService.MovieRevenue mr : movieRevenues) {
                    HBox movieBox = new HBox(10);
                    movieBox.setPadding(new Insets(10));
                    movieBox.setStyle(
                            "-fx-background-color: white; -fx-background-radius: 5; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");
                    movieBox.setAlignment(Pos.CENTER_LEFT);

                    VBox infoBox = new VBox(5);
                    Label nameLabel = new Label(mr.getMovieTitle());
                    nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                    Label detailLabel = new Label(String.format("%d suất chiếu • %d vé bán",
                            mr.getTotalShowtimes(), mr.getTotalTickets()));
                    detailLabel.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 12px;");

                    infoBox.getChildren().addAll(nameLabel, detailLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    Label revenueLabel = new Label(String.format("%,d VNĐ", mr.getTotalRevenue().longValue()));
                    revenueLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

                    movieBox.getChildren().addAll(infoBox, spacer, revenueLabel);
                    container.getChildren().add(movieBox);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Label errorLabel = new Label("❌ Lỗi khi tải dữ liệu: " + e.getMessage());
            errorLabel.setStyle("-fx-text-fill: #e74c3c;");
            container.getChildren().add(errorLabel);
        }
    }

    private HBox createStatRow(String label, String value) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);

        Label labelText = new Label(label);
        labelText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        labelText.setPrefWidth(200);

        Label valueText = new Label(value);
        valueText.setStyle("-fx-font-size: 14px; -fx-text-fill: #27ae60;");

        row.getChildren().addAll(labelText, valueText);
        return row;
    }
}
