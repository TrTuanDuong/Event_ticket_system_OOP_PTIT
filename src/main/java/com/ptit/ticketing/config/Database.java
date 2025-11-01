package com.ptit.ticketing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class quản lý kết nối cơ sở dữ liệu PostgreSQL (có thể chạy OFFLINE).
 */
public class Database {

    private static Database instance;
    private HikariDataSource ds;

    private Database() {
        try {
            // Load config từ application.properties
            Properties props = new Properties();
            try (InputStream is = getClass().getResourceAsStream("/application.properties")) {
                if (is == null) {
                    throw new RuntimeException("application.properties not found");
                }
                props.load(is);
            }

            // Configure HikariCP
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.user"));
            config.setPassword(props.getProperty("db.password"));
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("db.poolSize", "10")));

            // Test connection
            ds = new HikariDataSource(config);
            try (Connection conn = ds.getConnection()) {
                System.out.println("✅ Database connection successful!");
                System.out.println("   Connected to: " + props.getProperty("db.url"));
            }
        } catch (Exception e) {
            System.err.println("⚠️ Không thể kết nối Database. Chạy chế độ OFFLINE để xem giao diện JavaFX.");
            System.err.println("   Error: " + e.getMessage());
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
