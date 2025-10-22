package com.ptit.ticketing.model;

public class Report {
    private String movieName;
    private String showtime;
    private int ticketsSold;
    private double revenue;

    public Report(String movieName, String showtime, int ticketsSold, double revenue) {
        this.movieName = movieName;
        this.showtime = showtime;
        this.ticketsSold = ticketsSold;
        this.revenue = revenue;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
