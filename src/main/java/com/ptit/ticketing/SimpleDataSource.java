package com.ptit.ticketing;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * SimpleDataSource — lớp kết nối cơ bản đến PostgreSQL qua JDBC.
 * Dùng cho JavaFX Desktop App (không cần Spring).
 */
public class SimpleDataSource implements DataSource {
    private final String url;
    private final String username;
    private final String password;

    /**
     * Khởi tạo datasource với thông tin kết nối.
     * @param url JDBC URL, ví dụ: jdbc:postgresql://localhost:5432/cinema
     * @param Cloudu Tên đăng nhập PostgreSQL
     * @param 123456 Mật khẩu PostgreSQL
     */
    public SimpleDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            // Đăng ký driver PostgreSQL (bắt buộc cho JDBC thuần)
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ PostgreSQL JDBC Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ PostgreSQL JDBC Driver not found!", e);
        }
    }

    /**
     * Lấy kết nối mặc định.
     */
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Lấy kết nối với user/pass chỉ định.
     */
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Các method còn lại chỉ để implement interface, có thể để trống
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("Not a wrapper.");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {}

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {}

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return Logger.getGlobal();
    }
}
