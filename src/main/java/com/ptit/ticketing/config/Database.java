package com.ptit.ticketing.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

public class Database {
  private static Database instance;
  private final HikariDataSource ds;

  private Database() {
    try {
      Properties p = new Properties();
      try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
        p.load(in);
      }
      HikariConfig cfg = new HikariConfig();
      cfg.setJdbcUrl(p.getProperty("db.url"));
      cfg.setUsername(p.getProperty("db.user"));
      cfg.setPassword(p.getProperty("db.password"));
      cfg.setMaximumPoolSize(Integer.parseInt(p.getProperty("db.poolSize","10")));
      cfg.setSchema(p.getProperty("db.schema","public"));
      cfg.setAutoCommit(false);
      this.ds = new HikariDataSource(cfg);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static synchronized Database get() {
    if (instance == null) instance = new Database();
    return instance;
  }

  public DataSource ds() { return ds; }
}
