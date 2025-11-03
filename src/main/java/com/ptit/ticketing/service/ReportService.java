package com.ptit.ticketing.service;

import com.ptit.ticketing.util.Tx;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for generating reports and statistics
 */
public class ReportService extends BaseService {
    
    public ReportService(DataSource ds) {
        super(ds);
    }

    /**
     * Thống kê doanh thu theo ngày
     */
    public static class DailyRevenue {
        public LocalDate date;
        public BigDecimal totalRevenue;
        public int totalBookings;
        public int totalTickets;

        public DailyRevenue(LocalDate date, BigDecimal totalRevenue, int totalBookings, int totalTickets) {
            this.date = date;
            this.totalRevenue = totalRevenue;
            this.totalBookings = totalBookings;
            this.totalTickets = totalTickets;
        }

        public LocalDate getDate() { return date; }
        public BigDecimal getTotalRevenue() { return totalRevenue; }
        public int getTotalBookings() { return totalBookings; }
        public int getTotalTickets() { return totalTickets; }
    }

    /**
     * Thống kê doanh thu theo phim
     */
    public static class MovieRevenue {
        public String movieTitle;
        public int totalShowtimes;
        public int totalTickets;
        public BigDecimal totalRevenue;

        public MovieRevenue(String movieTitle, int totalShowtimes, int totalTickets, BigDecimal totalRevenue) {
            this.movieTitle = movieTitle;
            this.totalShowtimes = totalShowtimes;
            this.totalTickets = totalTickets;
            this.totalRevenue = totalRevenue;
        }

        public String getMovieTitle() { return movieTitle; }
        public int getTotalShowtimes() { return totalShowtimes; }
        public int getTotalTickets() { return totalTickets; }
        public BigDecimal getTotalRevenue() { return totalRevenue; }
    }

    /**
     * Lấy thống kê doanh thu theo ngày trong khoảng thời gian
     */
    public List<DailyRevenue> getDailyRevenue(LocalDate fromDate, LocalDate toDate) {
        return Tx.inReadOnly(ds, conn -> {
            String sql = """
                    SELECT 
                        DATE(b.created_at) as booking_date,
                        SUM(b.total_amount) as total_revenue,
                        COUNT(DISTINCT b.id) as total_bookings,
                        COUNT(t.id) as total_tickets
                    FROM api_booking b
                    LEFT JOIN api_ticket t ON b.id = t.booking_id
                    WHERE b.status = 'paid'
                        AND DATE(b.created_at) BETWEEN ? AND ?
                    GROUP BY DATE(b.created_at)
                    ORDER BY booking_date DESC
                    """;
            
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setDate(1, Date.valueOf(fromDate));
                st.setDate(2, Date.valueOf(toDate));
                
                List<DailyRevenue> results = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        results.add(new DailyRevenue(
                            rs.getDate("booking_date").toLocalDate(),
                            rs.getBigDecimal("total_revenue"),
                            rs.getInt("total_bookings"),
                            rs.getInt("total_tickets")
                        ));
                    }
                }
                return results;
            }
        });
    }

    /**
     * Lấy thống kê doanh thu theo phim
     */
    public List<MovieRevenue> getMovieRevenue(LocalDate fromDate, LocalDate toDate) {
        return Tx.inReadOnly(ds, conn -> {
            String sql = """
                    SELECT 
                        m.title as movie_title,
                        COUNT(DISTINCT s.id) as total_showtimes,
                        COUNT(t.id) as total_tickets,
                        SUM(t.price) as total_revenue
                    FROM api_movie m
                    LEFT JOIN api_showtime s ON m.id = s.movie_id
                    LEFT JOIN api_ticket t ON s.id = t.showtime_id
                    LEFT JOIN api_booking b ON t.booking_id = b.id
                    WHERE (b.status = 'paid' OR b.status IS NULL)
                        AND (DATE(b.created_at) BETWEEN ? AND ? OR b.created_at IS NULL)
                    GROUP BY m.id, m.title
                    HAVING COUNT(t.id) > 0
                    ORDER BY total_revenue DESC
                    """;
            
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setDate(1, Date.valueOf(fromDate));
                st.setDate(2, Date.valueOf(toDate));
                
                List<MovieRevenue> results = new ArrayList<>();
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        results.add(new MovieRevenue(
                            rs.getString("movie_title"),
                            rs.getInt("total_showtimes"),
                            rs.getInt("total_tickets"),
                            rs.getBigDecimal("total_revenue")
                        ));
                    }
                }
                return results;
            }
        });
    }

    /**
     * Export báo cáo doanh thu ra Excel
     */
    public void exportRevenueToExcel(LocalDate fromDate, LocalDate toDate, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            // Sheet 1: Tổng quan
            Sheet summarySheet = workbook.createSheet("Tổng quan");
            createSummarySheet(summarySheet, fromDate, toDate);

            // Sheet 2: Doanh thu theo ngày
            Sheet dailySheet = workbook.createSheet("Theo ngày");
            createDailyRevenueSheet(dailySheet, fromDate, toDate);

            // Sheet 3: Doanh thu theo phim
            Sheet movieSheet = workbook.createSheet("Theo phim");
            createMovieRevenueSheet(movieSheet, fromDate, toDate);

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                for (int j = 0; j < 5; j++) {
                    sheet.autoSizeColumn(j);
                }
            }

            // Ghi file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        }
    }

    private void createSummarySheet(Sheet sheet, LocalDate fromDate, LocalDate toDate) {
        // Style cho header
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerStyle.setFont(headerFont);

        // Title
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("BÁO CÁO DOANH THU RạP CHIẾU PHIM");
        titleCell.setCellStyle(headerStyle);

        // Khoảng thời gian
        Row periodRow = sheet.createRow(1);
        periodRow.createCell(0).setCellValue("Từ ngày: " + fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        periodRow.createCell(2).setCellValue("Đến ngày: " + toDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        sheet.createRow(2); // Empty row

        // Lấy tổng quan
        Map<String, Object> summary = getSummary(fromDate, toDate);

        int rowNum = 3;
        String[] labels = {
            "Tổng doanh thu:",
            "Tổng số vé bán:",
            "Tổng số booking:",
            "Doanh thu trung bình/ngày:",
            "Số vé trung bình/ngày:"
        };

        for (String label : labels) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(label);
            Cell valueCell = row.createCell(1);
            
            switch (label) {
                case "Tổng doanh thu:" -> valueCell.setCellValue(
                    String.format("%,d VNĐ", ((BigDecimal) summary.get("totalRevenue")).longValue())
                );
                case "Tổng số vé bán:" -> valueCell.setCellValue((Integer) summary.get("totalTickets"));
                case "Tổng số booking:" -> valueCell.setCellValue((Integer) summary.get("totalBookings"));
                case "Doanh thu trung bình/ngày:" -> valueCell.setCellValue(
                    String.format("%,d VNĐ", ((BigDecimal) summary.get("avgDailyRevenue")).longValue())
                );
                case "Số vé trung bình/ngày:" -> valueCell.setCellValue(
                    String.format("%.1f vé", (Double) summary.get("avgDailyTickets"))
                );
            }
        }
    }

    private void createDailyRevenueSheet(Sheet sheet, LocalDate fromDate, LocalDate toDate) {
        // Header style
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Ngày", "Doanh thu (VNĐ)", "Số booking", "Số vé"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Data rows
        List<DailyRevenue> dailyRevenues = getDailyRevenue(fromDate, toDate);
        int rowNum = 1;
        for (DailyRevenue dr : dailyRevenues) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dr.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            row.createCell(1).setCellValue(dr.getTotalRevenue().longValue());
            row.createCell(2).setCellValue(dr.getTotalBookings());
            row.createCell(3).setCellValue(dr.getTotalTickets());
        }
    }

    private void createMovieRevenueSheet(Sheet sheet, LocalDate fromDate, LocalDate toDate) {
        // Header style
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Tên phim", "Số suất chiếu", "Số vé bán", "Doanh thu (VNĐ)"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Data rows
        List<MovieRevenue> movieRevenues = getMovieRevenue(fromDate, toDate);
        int rowNum = 1;
        for (MovieRevenue mr : movieRevenues) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(mr.getMovieTitle());
            row.createCell(1).setCellValue(mr.getTotalShowtimes());
            row.createCell(2).setCellValue(mr.getTotalTickets());
            row.createCell(3).setCellValue(mr.getTotalRevenue().longValue());
        }
    }

    private Map<String, Object> getSummary(LocalDate fromDate, LocalDate toDate) {
        return Tx.inReadOnly(ds, conn -> {
            String sql = """
                    SELECT 
                        COALESCE(SUM(b.total_amount), 0) as total_revenue,
                        COUNT(DISTINCT b.id) as total_bookings,
                        COUNT(t.id) as total_tickets
                    FROM api_booking b
                    LEFT JOIN api_ticket t ON b.id = t.booking_id
                    WHERE b.status = 'paid'
                        AND DATE(b.created_at) BETWEEN ? AND ?
                    """;
            
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                st.setDate(1, Date.valueOf(fromDate));
                st.setDate(2, Date.valueOf(toDate));
                
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        Map<String, Object> summary = new HashMap<>();
                        BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
                        int totalBookings = rs.getInt("total_bookings");
                        int totalTickets = rs.getInt("total_tickets");
                        
                        long days = java.time.temporal.ChronoUnit.DAYS.between(fromDate, toDate) + 1;
                        
                        summary.put("totalRevenue", totalRevenue);
                        summary.put("totalBookings", totalBookings);
                        summary.put("totalTickets", totalTickets);
                        summary.put("avgDailyRevenue", totalRevenue.divide(BigDecimal.valueOf(days), 2, java.math.RoundingMode.HALF_UP));
                        summary.put("avgDailyTickets", (double) totalTickets / days);
                        
                        return summary;
                    }
                    return new HashMap<>();
                }
            }
        });
    }
}
