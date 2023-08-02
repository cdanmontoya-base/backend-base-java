package com.cdanmontoya.base.infrastructure.acl.translators.insertaccount;

import com.cdanmontoya.base.application.commands.InsertAccount;
import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;
import io.vavr.collection.List;

public class InsertAccountRequestDtoTranslator {

  private InsertAccountRequestDtoTranslator() {
    throw new IllegalStateException("Utility class");
  }

  public static InsertAccount of(InsertAccountRequestDto requestDto) {
    return new InsertAccount(
        requestDto.email(),
        List.ofAll(requestDto.cellphones())
    );
  }

}
