package com.ptit.ticketing.repo;

import javax.sql.DataSource;

public abstract class BaseRepo {
  protected final DataSource ds;
  protected BaseRepo(DataSource ds) { this.ds = ds; }
}
