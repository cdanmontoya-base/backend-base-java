package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class CellphoneMapper implements RowMapper<String> {

  @Override
  public String map(ResultSet rs, StatementContext ctx) throws SQLException {
    return rs.getString("c_number");
  }

}
