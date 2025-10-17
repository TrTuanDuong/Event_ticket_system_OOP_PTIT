package com.ptit.ticketing.service;

import com.ptit.ticketing.auth.DjangoPassword;
import javax.sql.DataSource;
import java.sql.*;

public class AuthService extends BaseService {
  public AuthService(DataSource ds) { super(ds); }

  public boolean login(String username, String password) {
    try (Connection c = ds.getConnection();
         PreparedStatement st = c.prepareStatement("select password from auth_user where username=?")) {
      st.setString(1, username);
      ResultSet rs = st.executeQuery();
      if (rs.next()) return DjangoPassword.verify(password, rs.getString(1));
      return false;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
