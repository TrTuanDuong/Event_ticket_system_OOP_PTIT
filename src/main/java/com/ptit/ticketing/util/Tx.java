package com.ptit.ticketing.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Tx {
  @FunctionalInterface
  public interface SqlFunction<T, R> {
    R apply(T t) throws SQLException;
  }

  public static <T> T withTx(DataSource ds, SqlFunction<Connection, T> fn) {
    try (Connection c = ds.getConnection()) {
      try {
        c.setAutoCommit(false);
        T r = fn.apply(c);
        c.commit();
        return r;
      } catch (Exception e) {
        c.rollback();
        throw new RuntimeException("Transaction failed", e);
      }
    } catch (Exception e) {
      throw new RuntimeException("Connection failed", e);
    }
  }
}
