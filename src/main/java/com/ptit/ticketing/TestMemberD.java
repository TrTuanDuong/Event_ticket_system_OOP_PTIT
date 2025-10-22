package com.ptit.ticketing;

import com.ptit.ticketing.service.*;
import com.ptit.ticketing.domain.Ticket;

import javax.sql.DataSource;
import java.util.*;

public class TestMemberD {
    public static void main(String[] args) {
        // ⚙️ Cấu hình PostgreSQL
        DataSource ds = new SimpleDataSource(
                "jdbc:postgresql://localhost:5432/cinema", // tên DB
                "Cloudu", // user
                "123456" // mật khẩu thật
        );

        // Tạo các service để test phần bạn làm
        TicketService ticketService = new TicketService(ds);
        ReportService reportService = new ReportService(ds);

        // ✅ Test 1: Lấy toàn bộ vé
        System.out.println("=== Danh sách vé ===");
        for (Ticket t : ticketService.getAllTickets()) {
            System.out.println(t);
        }

        // ✅ Test 2: Báo cáo doanh thu
        System.out.println("\n=== Báo cáo doanh thu ===");
        reportService.generateReports().forEach(r ->
                System.out.println(r.getMovieName() + " | " +
                        r.getShowtime() + " | " +
                        r.getTicketsSold() + " vé | " +
                        r.getRevenue() + " VND")
        );
    }
}
