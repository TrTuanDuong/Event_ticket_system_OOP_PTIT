package com.ptit.ticketing.service;

import com.ptit.ticketing.auth.DjangoPassword;
import com.ptit.ticketing.domain.User;
import com.ptit.ticketing.repo.UserRepo;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class AuthService extends BaseService {

  private UserRepo userRepo;

  public AuthService(DataSource ds) {
    super(ds);
    this.userRepo = new UserRepo(ds);
  }

  /**
   * Verify username và password, trả về User nếu đúng
   */
  public Optional<User> login(String username, String password) {
    try (Connection c = ds.getConnection()) {
      // Tìm user theo username
      Optional<User> userOpt = userRepo.findByUsername(c, username);

      if (userOpt.isEmpty()) {
        return Optional.empty();
      }

      User user = userOpt.get();

      // Verify password với Django hash
      if (DjangoPassword.verify(password, user.getPassword())) {
        return Optional.of(user);
      }

      return Optional.empty();

    } catch (Exception e) {
      throw new RuntimeException("Login failed", e);
    }
  }

  /**
   * Check password only (legacy method)
   */
  public boolean verifyPassword(String username, String password) {
    return login(username, password).isPresent();
  }
}
