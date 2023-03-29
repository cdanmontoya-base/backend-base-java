package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account;

import com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.cellphone.CellphoneMapper;
import java.util.Map;
import java.util.Objects;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

public class AccountCellphoneReducer implements LinkedHashMapRowReducer<String, AccountRecord> {

  @Override
  public void accumulate(Map<String, AccountRecord> container, RowView rowView) {
    AccountRecord accountRecord = container
        .computeIfAbsent(
            rowView.getColumn("a_id", String.class),
            email -> rowView.getRow(AccountRecord.class));

    if (Objects.nonNull(rowView.getColumn("c_number", String.class))) {
      accountRecord.cellphones().add(rowView.getRow(String.class));
    }

  }
}
