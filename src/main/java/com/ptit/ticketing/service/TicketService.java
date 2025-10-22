package com.ptit.ticketing.service;

import com.ptit.ticketing.repo.TicketRepo;
import com.ptit.ticketing.util.QRService;
import com.ptit.ticketing.domain.Ticket;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

public class TicketService extends BaseService {
    private final TicketRepo repo;
    private final QRService qr;

    public TicketService(DataSource ds) {
        super(ds);
        this.repo = new TicketRepo(ds);
        this.qr = new QRService();
    }

    public List<Ticket> getAllTickets() {
        try {
            return repo.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ticket> getTicketsByBooking(UUID bookingId) {
        try {
            return repo.findByBooking(bookingId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkIn(UUID ticketId) {
        try {
            repo.updateStatus(ticketId, "checked_in");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateQrForTicket(UUID ticketId, String codeText) {
        String filePath = "target/qrcodes/" + ticketId + ".png";
        qr.generate(codeText, filePath);
        return filePath;
    }
}
