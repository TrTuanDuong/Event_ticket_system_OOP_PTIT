package com.ptit.ticketing.service;

import com.ptit.ticketing.domain.User;

/**
 * Singleton class quản lý session của user hiện tại.
 * Lưu thông tin user đã login trong suốt phiên làm việc.
 */
public class SessionManager {

    private static SessionManager instance;
    private User currentUser;

    private SessionManager() {
        // Private constructor cho Singleton pattern
    }

    /**
     * Lấy instance duy nhất của SessionManager
     */
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Lưu thông tin user sau khi login thành công
     */
    public void login(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.currentUser = user;
        System.out.println("✅ User logged in: " + user.getUsername() + " (" + user.getRole() + ")");
    }

    /**
     * Đăng xuất user hiện tại
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("👋 User logged out: " + currentUser.getUsername());
            this.currentUser = null;
        }
    }

    /**
     * Lấy thông tin user hiện tại (static convenience method)
     */
    public static User getCurrentUser() {
        return getInstance().currentUser;
    }

    /**
     * Kiểm tra xem đã login chưa
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Kiểm tra user hiện tại có phải admin không
     */
    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }

    /**
     * Lấy username của user hiện tại
     */
    public String getCurrentUsername() {
        return currentUser != null ? currentUser.getUsername() : "Guest";
    }

    /**
     * Lấy full name của user hiện tại
     */
    public String getCurrentFullName() {
        if (currentUser == null)
            return "Guest";
        String fullName = currentUser.getFullName();
        return fullName != null ? fullName : currentUser.getUsername();
    }
}
