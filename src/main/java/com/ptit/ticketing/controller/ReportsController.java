    package com.ptit.ticketing.controller;
    import javafx.event.ActionEvent;

    import com.ptit.ticketing.config.Database;
    import com.ptit.ticketing.model.Report;
    import com.ptit.ticketing.service.ReportService;
    import com.ptit.ticketing.util.ExportService;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.stage.FileChooser;
    import javafx.stage.Stage;

    import java.io.File;
    import java.util.List;

    public class ReportsController {

        @FXML
        private TableView<Report> reportsTable;
        @FXML
        private TableColumn<Report, String> movieColumn;
        @FXML
        private TableColumn<Report, String> showtimeColumn;
        @FXML
        private TableColumn<Report, Integer> ticketsSoldColumn;
        @FXML
        private TableColumn<Report, Double> revenueColumn;

        @FXML
        private Button refreshButton;
        @FXML
        private Button exportPdfButton;
        @FXML
        private Button exportExcelButton;

        private final ReportService reportService = new ReportService(Database.get().ds());


        @FXML
        public void initialize() {
            movieColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));
            showtimeColumn.setCellValueFactory(new PropertyValueFactory<>("showtime"));
            ticketsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
            revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

            loadReports();

            refreshButton.setOnAction(e -> loadReports());
            exportPdfButton.setOnAction(this::exportToPDF);
            exportExcelButton.setOnAction(this::exportToExcel);

        }

        private void loadReports() {
            List<Report> reports = reportService.generateReports();
            ObservableList<Report> reportList = FXCollections.observableArrayList(reports);
            reportsTable.setItems(reportList);
        }

        @FXML
    public void exportToPDF(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF Report");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        fileChooser.setInitialFileName("report.pdf");

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                ExportService.exportToPdf(reportsTable.getItems(), file.getAbsolutePath());
                showAlert("Export Successful", "Report exported to PDF successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to export PDF: " + e.getMessage());
            }
        }
    }

    @FXML
    public void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel Report");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Excel Files", "*.xlsx")
        );
        fileChooser.setInitialFileName("report.xlsx");

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                ExportService.exportToExcel(reportsTable.getItems(), file.getAbsolutePath());
                showAlert("Export Successful", "Report exported to Excel successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to export Excel: " + e.getMessage());
            }
        }
    }


        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
