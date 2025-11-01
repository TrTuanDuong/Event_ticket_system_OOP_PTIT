package com.ptit.ticketing.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.util.function.Consumer;

/**
 * Singleton quản lý timer đặt vé (10 phút)
 */
public class SessionTimer {

    private static SessionTimer instance;

    private Timeline timeline;
    private int remainingSeconds;
    private static final int BOOKING_TIME_LIMIT_SECONDS = 10 * 60; // 10 phút

    private Consumer<Integer> onTick; // Callback mỗi giây
    private Runnable onTimeout; // Callback khi hết thời gian

    private SessionTimer() {
        // Private constructor for singleton
    }

    public static synchronized SessionTimer getInstance() {
        if (instance == null) {
            instance = new SessionTimer();
        }
        return instance;
    }

    /**
     * Bắt đầu timer 10 phút
     */
    public void startTimer(Consumer<Integer> tickCallback, Runnable timeoutCallback) {
        stopTimer(); // Stop any existing timer

        this.onTick = tickCallback;
        this.onTimeout = timeoutCallback;
        this.remainingSeconds = BOOKING_TIME_LIMIT_SECONDS;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            remainingSeconds--;

            // Callback với số giây còn lại
            if (onTick != null) {
                Platform.runLater(() -> onTick.accept(remainingSeconds));
            }

            // Kiểm tra timeout
            if (remainingSeconds <= 0) {
                stopTimer();
                if (onTimeout != null) {
                    Platform.runLater(() -> onTimeout.run());
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        System.out.println("⏰ Booking timer started: " + BOOKING_TIME_LIMIT_SECONDS + " seconds");
    }

    /**
     * Dừng timer
     */
    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
        remainingSeconds = 0;
        System.out.println("⏰ Booking timer stopped");
    }

    /**
     * Lấy thời gian còn lại (giây)
     */
    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    /**
     * Format thời gian còn lại thành MM:SS
     */
    public String getFormattedTime() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Kiểm tra timer đang chạy
     */
    public boolean isRunning() {
        return timeline != null && timeline.getStatus() == Timeline.Status.RUNNING;
    }

    /**
     * Reset timer về 10 phút (không start lại)
     */
    public void reset() {
        stopTimer();
        remainingSeconds = BOOKING_TIME_LIMIT_SECONDS;
    }
}
