package com.ptit.ticketing.repo;

import com.ptit.ticketing.domain.User;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Repository for User entity
 * Maps to Django api_user table
 */
public class UserRepo extends BaseRepo {
    public UserRepo(DataSource ds) {
        super(ds);
    }

    /**
     * Find user by username
     */
    public Optional<User> findByUsername(Connection c, String username) throws SQLException {
        String sql = "SELECT id, username, password, first_name, last_name, email, phone, role, " +
                "date_of_birth, is_active, is_staff, is_superuser, date_joined, last_login " +
                "FROM api_user WHERE username = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Find user by ID
     */
    public Optional<User> findById(Connection c, UUID id) throws SQLException {
        String sql = "SELECT id, username, password, first_name, last_name, email, phone, role, " +
                "date_of_birth, is_active, is_staff, is_superuser, date_joined, last_login " +
                "FROM api_user WHERE id = ?";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setObject(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Find all users
     */
    public List<User> findAll(Connection c) throws SQLException {
        String sql = "SELECT id, username, password, first_name, last_name, email, phone, role, " +
                "date_of_birth, is_active, is_staff, is_superuser, date_joined, last_login " +
                "FROM api_user ORDER BY username";
        try (PreparedStatement st = c.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(mapRow(rs));
            }
            return users;
        }
    }

    /**
     * Find users by role
     */
    public List<User> findByRole(Connection c, String role) throws SQLException {
        String sql = "SELECT id, username, password, first_name, last_name, email, phone, role, " +
                "date_of_birth, is_active, is_staff, is_superuser, date_joined, last_login " +
                "FROM api_user WHERE role = ? ORDER BY username";
        try (PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, role);
            try (ResultSet rs = st.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    users.add(mapRow(rs));
                }
                return users;
            }
        }
    }

    /**
     * Verify user credentials (for login)
     * Note: Django uses pbkdf2_sha256 hashing - you'll need to implement proper
     * password verification
     */
    public boolean verifyPassword(String hashedPassword, String plainPassword) {
        // TODO: Implement Django password verification
        // For now, just compare directly (NOT SECURE - for testing only)
        return hashedPassword != null && hashedPassword.equals(plainPassword);
    }

    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId((UUID) rs.getObject("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));

        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        u.setFullName((firstName != null ? firstName : "") + " " + (lastName != null ? lastName : ""));

        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));
        u.setRole(rs.getString("role"));

        LocalDate dob = rs.getObject("date_of_birth", LocalDate.class);
        u.setDateOfBirth(dob);

        u.setActive(rs.getBoolean("is_active"));
        u.setStaff(rs.getBoolean("is_staff"));
        u.setSuperuser(rs.getBoolean("is_superuser"));

        OffsetDateTime dateJoined = rs.getObject("date_joined", OffsetDateTime.class);
        u.setCreatedAt(dateJoined);

        OffsetDateTime lastLogin = rs.getObject("last_login", OffsetDateTime.class);
        u.setUpdatedAt(lastLogin);

        return u;
    }
}
