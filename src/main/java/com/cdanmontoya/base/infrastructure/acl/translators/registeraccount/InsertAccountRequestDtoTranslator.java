package com.cdanmontoya.base.infrastructure.acl.translators.registeraccount;

import com.cdanmontoya.base.application.commands.InsertAccount;
import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;

public class InsertAccountRequestDtoTranslator {

  public static InsertAccount of(InsertAccountRequestDto requestDto) {
    return new InsertAccount(
        requestDto.email(),
        requestDto.cellphones()
    );
  }

}
