package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class AccountJoinMapper implements RowMapper<AccountRecord> {

  @Override
  public AccountRecord map(ResultSet rs, StatementContext ctx) throws SQLException {
    return new AccountRecord(
        UUID.fromString(rs.getString("a_id")),
        rs.getString("a_email"),
        new ArrayList<>()
    );
  }
}
