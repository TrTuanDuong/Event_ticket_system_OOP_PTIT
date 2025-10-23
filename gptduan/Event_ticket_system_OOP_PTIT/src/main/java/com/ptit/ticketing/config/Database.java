package com.ptit.ticketing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class quản lý kết nối cơ sở dữ liệu PostgreSQL (có thể chạy OFFLINE).
 */
public class Database {

    private static Database instance;
    private HikariDataSource ds;

    private Database() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/ticketdb");
            config.setUsername("postgres");
            config.setPassword(""); // điền mật khẩu thật nếu có PostgreSQL

            ds = new HikariDataSource(config);
            System.out.println("✅ Đã kết nối PostgreSQL thành công!");
        } catch (Exception e) {
            System.out.println("⚠️ Không thể kết nối Database. Chạy chế độ OFFLINE để xem giao diện JavaFX.");
            ds = null;
        }
    }

    /** Trả về đối tượng Database duy nhất (singleton pattern) */
    public static synchronized Database get() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /** Trả về DataSource, có thể null nếu OFFLINE */
    public HikariDataSource ds() {
        return ds;
    }

    /** Lấy Connection trực tiếp (nếu có database thật) */
    public Connection getConnection() throws SQLException {
        if (ds == null) {
            throw new SQLException("Đang chạy ở chế độ OFFLINE, chưa kết nối database.");
        }
        return ds.getConnection();
    }
}
