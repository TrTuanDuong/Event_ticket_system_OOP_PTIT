package com.ptit.ticketing.ui;

import com.ptit.ticketing.repo.DashboardRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private Label totalMoviesLabel;
    @FXML private Label upcomingShowtimesLabel;
    @FXML private Label totalRevenueLabel;

    private DashboardRepository dashboardRepository = new DashboardRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadStatistics();
    }

    private void loadStatistics() {
        int totalMovies = dashboardRepository.countTotalMovies();
        int upcomingShowtimes = dashboardRepository.countUpcomingShowtimes();
        double totalRevenue = dashboardRepository.getTotalRevenue();

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        totalMoviesLabel.setText(String.valueOf(totalMovies));
        upcomingShowtimesLabel.setText(String.valueOf(upcomingShowtimes));
        totalRevenueLabel.setText(currencyFormatter.format(totalRevenue));
    }
}