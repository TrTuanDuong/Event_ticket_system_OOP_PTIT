package com.ptit.ticketing.service;

import javax.sql.DataSource;

public abstract class BaseService {
  protected final DataSource ds;
  protected BaseService(DataSource ds) { this.ds = ds; }
}
