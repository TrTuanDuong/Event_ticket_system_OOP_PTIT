package com.ptit.ticketing.util;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

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

  public static boolean run(Object object) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'run'");
  }

  public static void init(PGSimpleDataSource ds) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'init'");
  }
}
