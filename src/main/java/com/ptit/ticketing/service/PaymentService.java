package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.Booking;
import com.ptit.ticketing.repo.BookingRepo;
import com.ptit.ticketing.repo.PaymentRepo;
import com.ptit.ticketing.util.Tx;

import java.time.LocalDateTime;

public class PaymentService {
    private final PaymentRepo paymentRepo;
    private final BookingRepo bookingRepo;

    public PaymentService(PaymentRepo paymentRepo, BookingRepo bookingRepo) {
        this.paymentRepo = paymentRepo;
        this.bookingRepo = bookingRepo;
    }

    public boolean processPayment(int bookingId, double amount, String method) {
        return (Boolean) Tx.run(new java.util.concurrent.Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                paymentRepo.insert(bookingId, amount, method, LocalDateTime.now());
                bookingRepo.updateStatus(bookingId, "PAID");
                return true;
            }
        });
    }
}
