package com.ptit.ticketing.ui;

import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.domain.Showtime;
import com.ptit.ticketing.factory.SeatNodeFactory;
import com.ptit.ticketing.repo.SeatRepository;
import com.ptit.ticketing.repo.ShowtimeRepository;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.math.BigDecimal;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.ResourceBundle;

public class SeatMapController implements Initializable {

    @FXML private ComboBox<Showtime> showtimeComboBox;
    @FXML private GridPane seatGridPane;
    @FXML private Label selectedInfoLabel;
    @FXML private Button bookingButton;

    private final ShowtimeRepository showtimeRepository = new ShowtimeRepository();
    private final SeatRepository seatRepository = new SeatRepository();
    private final SeatNodeFactory seatFactory = new SeatNodeFactory();

    private Set<Seat> selectedSeats = new HashSet<>();
    private Showtime currentShowtime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadShowtimes();

        showtimeComboBox.getSelectionModel().selectedItemProperty().addListener(
            (options, oldShowtime, newShowtime) -> {
                if (newShowtime != null) {
                    this.currentShowtime = newShowtime;
                    loadSeatMap(newShowtime);
                }
            }
        );
        
        updateBookingButtonState();
    }

    private void loadShowtimes() {
        List<Showtime> showtimes = showtimeRepository.findAllUpcomingShowtimes();
        showtimeComboBox.setItems(FXCollections.observableArrayList(showtimes));
    }

    private void loadSeatMap(Showtime showtime) {
        seatGridPane.getChildren().clear();
        selectedSeats.clear();
        updateSelectedInfoLabel();
        updateBookingButtonState();

        UUID auditoriumId = showtime.getAuditoriumId();
        UUID showtimeId = showtime.getId();

        List<Seat> seats = seatRepository.findSeatsByAuditoriumId(auditoriumId);
        Set<UUID> bookedSeatIds = seatRepository.findBookedSeatIds(showtimeId);

        for (Seat seat : seats) {
            boolean isBooked = bookedSeatIds.contains(seat.getId());
            Node seatNode = seatFactory.createSeatNode(seat, isBooked);

            if (!isBooked) {
                seatNode.setOnMouseClicked(e -> handleSeatClick(seat, seatNode));
            }
            
            int row = convertRowLabelToIndex(seat.getRowLabel());
            int col = seat.getSeatNumber(); 
            
            seatGridPane.add(seatNode, col, row);

            if ("couple".equalsIgnoreCase(seat.getSeatType())) {
                GridPane.setColumnSpan(seatNode, 2);
            }
        }
    }

    private int convertRowLabelToIndex(String rowLabel) {
        return rowLabel.toUpperCase().charAt(0) - 'A';
    }

    private void handleSeatClick(Seat seat, Node seatNode) {
        if (selectedSeats.contains(seat)) {
            selectedSeats.remove(seat);
            seatNode.getStyleClass().remove("seat-selected");
        } else {
            selectedSeats.add(seat);
            seatNode.getStyleClass().add("seat-selected");
        }
        updateSelectedInfoLabel();
        updateBookingButtonState();
    }

    private void updateSelectedInfoLabel() {
        if (selectedSeats.isEmpty()) {
            selectedInfoLabel.setText("Chưa chọn ghế nào.");
            return;
        }

        BigDecimal basePrice = currentShowtime.getBasePrice();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Seat seat : selectedSeats) {
            BigDecimal multiplier = BigDecimal.valueOf(seat.getPriceMultiplier());
            totalPrice = totalPrice.add(basePrice.multiply(multiplier));
        }

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        selectedInfoLabel.setText(
            String.format("%d ghế. Tổng tiền: %s", selectedSeats.size(), currencyFormatter.format(totalPrice))
        );
    }

    private void updateBookingButtonState() {
        bookingButton.setDisable(selectedSeats.isEmpty());
    }
}