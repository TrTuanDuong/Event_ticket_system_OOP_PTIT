package com.ptit.ticketing.factory;

import com.ptit.ticketing.domain.Seat;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Áp dụng FACTORY PATTERN
 * Tạo Node (Button) dựa trên Seat.java (dùng String)
 */
public class SeatNodeFactory {

    public Node createSeatNode(Seat seat, boolean isBooked) {
        Button seatNode = new Button(seat.getSeatLabel()); // Dùng "A1", "B2"
        seatNode.setPrefSize(45, 45);

        // 1. Thêm style chung
        seatNode.getStyleClass().add("seat-node");

        // 2. Phân loại Standard/VIP/Couple (Nhiệm vụ của B)
        // Dùng equals() cho String, và toLowerCase() để đảm bảo
        switch (seat.getSeatType().toLowerCase()) {
            case "standard":
                seatNode.getStyleClass().add("seat-standard");
                break;
            case "vip":
                seatNode.getStyleClass().add("seat-vip");
                break;
            case "couple":
                seatNode.getStyleClass().add("seat-couple");
                seatNode.setPrefWidth(95); // Ghế đôi rộng hơn
                break;
            default:
                 seatNode.getStyleClass().add("seat-standard");
                 break;
        }

        // 3. Xử lý ghế đã bị đặt (tải từ repo)
        if (isBooked) {
            seatNode.getStyleClass().add("seat-booked");
            seatNode.setDisable(true); // Vô hiệu hóa nút
        }

        return seatNode;
    }
}