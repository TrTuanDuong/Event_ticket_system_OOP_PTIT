package com.ptit.ticketing.ui;

import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.domain.Seat;
import com.ptit.ticketing.service.BookingService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.OffsetDateTime;

public class BookingController {

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtShowtimeId;

    @FXML
    private ListView<String> seatListView;

    @FXML
    private Button btnCreateBooking;

    @FXML
    private Label lblStatus;

    private BookingService bookingService;

    private final ObservableList<String> selectedSeats = FXCollections.observableArrayList();

    // Hàm khởi tạo controller
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Gọi khi giao diện được load
    @FXML
    public void initialize() {
        seatListView.setItems(selectedSeats);
        lblStatus.setText("Ready to create booking");
    }

    // Thêm ghế vào danh sách đặt
    @FXML
    public void handleAddSeat(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Seat");
        dialog.setHeaderText("Enter seat ID to add:");
        dialog.setContentText("Seat ID:");

        dialog.showAndWait().ifPresent(seatId -> {
            selectedSeats.add(seatId);
        });
    }
    // Xử lý tạo booking
    @FXML
    public void handleCreateBooking(ActionEvent event) {
        try {
            UUID userId = UUID.fromString(txtUserId.getText());
            int showtimeId = Integer.parseInt(txtShowtimeId.getText());
            
            // Showtimes are not available from BookingService in this codebase;
            // use the current time as the booking showtime or extend BookingService to provide it.
            OffsetDateTime showtimeStart = OffsetDateTime.now();

            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setShowtimeStart(showtimeStart);
            booking.setStatus("PENDING");

            List<Seat> seats = new ArrayList<>();
            for (String seatIdStr : selectedSeats) {
                Seat seat = new Seat();
                seat.setId(UUID.fromString(seatIdStr));
                seats.add(seat);
            }

            boolean success = bookingService.createBooking(booking, seats);
            if (success) {
                lblStatus.setText("Booking created successfully!");
                selectedSeats.clear();
            } else {
                lblStatus.setText("Booking failed!");
            }

        } catch (IllegalArgumentException e) {
            lblStatus.setText("Error: invalid user ID format");
        } catch (Exception e) {
            lblStatus.setText("Error: " + e.getMessage());
    }
    }
}

