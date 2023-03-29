package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account;

import com.cdanmontoya.base.domain.model.AccountId;
import com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone.CellphoneDao;
import com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone.CellphoneMapper;
import java.util.Collection;
import org.jdbi.v3.sqlobject.CreateSqlObject;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import reactor.core.publisher.Mono;

@RegisterRowMapper(AccountMapper.class)
public interface AccountDao {

  @CreateSqlObject
  CellphoneDao cellphoneDao();


  @SqlQuery("""
      SELECT a.id a_id, a.email a_email, c.number c_number
      FROM accounts a
      INNER JOIN cellphones c ON a.id = c.account_id 
      """)
  @RegisterRowMapper(value = AccountJoinMapper.class)
  @RegisterRowMapper(value = CellphoneMapper.class)
  @UseRowReducer(AccountCellphoneReducer.class)
  Collection<AccountRecord> findAll();

  @Transaction
  default Mono<AccountRecord> insert(AccountRecord accountRecord) {
    try {
      insertAccount(accountRecord);

      accountRecord
          .cellphones()
          .forEach(number -> cellphoneDao().insert(accountRecord.id(), number));

      return Mono.just(accountRecord);
    } catch (Exception e) {
      return Mono.error(e);
    }
  }

  @SqlQuery("""
      SELECT a.id a_id, a.email a_email, c.number c_number
      FROM accounts a
      INNER JOIN cellphones c ON a.id = c.account_id 
      WHERE id = :id.id""")
  @RegisterRowMapper(value = AccountJoinMapper.class)
  @RegisterRowMapper(value = CellphoneMapper.class)
  @UseRowReducer(AccountCellphoneReducer.class)
  AccountRecord findById(@BindMethods("id") AccountId id);

  @SqlUpdate("INSERT INTO accounts (id, email) VALUES (:account.id, :account.email)")
  void insertAccount(@BindMethods("account") AccountRecord accountRecord);

  @SqlQuery("DELETE FROM accounts a WHERE id = :id.id RETURNING *")
  AccountRecord delete(@BindMethods("id") AccountId accountId);

}
