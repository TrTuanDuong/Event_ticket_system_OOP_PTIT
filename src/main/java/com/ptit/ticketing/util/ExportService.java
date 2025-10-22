package com.ptit.ticketing.util;

import com.ptit.ticketing.model.Report;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;

import java.io.FileOutputStream;
import java.util.List;

public class ExportService {

    // =============================
    // ====== EXPORT TO PDF ========
    // =============================
    public static void exportToPdf(List<Report> reports, String filePath) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Tiêu đề
        com.itextpdf.text.Font titleFont =
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
        Paragraph title = new Paragraph("Báo cáo Doanh thu", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Bảng dữ liệu
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{4, 3, 2, 2});

        addTableHeaderPDF(table, "Phim", "Suất chiếu", "Vé bán", "Doanh thu");

        for (Report r : reports) {
            table.addCell(r.getMovieName());
            table.addCell(r.getShowtime());
            table.addCell(String.valueOf(r.getTicketsSold()));
            table.addCell(String.format("%.2f", r.getRevenue()));
        }

        document.add(table);
        document.close();
    }

    private static void addTableHeaderPDF(PdfPTable table, String... headers) {
        com.itextpdf.text.Font headerFont =
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }
    }

    // ===============================
    // ====== EXPORT TO EXCEL ========
    // ===============================
    public static void exportToExcel(List<Report> reports, String filePath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Báo cáo Doanh thu");

        // Style header
        CellStyle headerStyle = workbook.createCellStyle();
        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Phim", "Suất chiếu", "Vé bán", "Doanh thu"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // Data rows
        int rowNum = 1;
        for (Report r : reports) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(r.getMovieName());
            row.createCell(1).setCellValue(r.getShowtime());
            row.createCell(2).setCellValue(r.getTicketsSold());
            row.createCell(3).setCellValue(r.getRevenue());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}
