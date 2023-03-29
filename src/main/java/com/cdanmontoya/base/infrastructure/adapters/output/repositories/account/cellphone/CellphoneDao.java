package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone;

import com.cdanmontoya.base.domain.model.AccountId;
import java.util.UUID;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CellphoneDao {

  @SqlUpdate("""
      INSERT INTO cellphones(account_id, number)
      VALUES (:accountId, :number)
      """)
  void insert(@Bind("accountId") UUID accountId, @Bind("number") String number);
}
