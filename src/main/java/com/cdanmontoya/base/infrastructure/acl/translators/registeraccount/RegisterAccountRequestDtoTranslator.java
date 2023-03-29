package com.cdanmontoya.base.infrastructure.acl.translators.registeraccount;

import com.cdanmontoya.base.application.commands.RegisterAccount;
import com.cdanmontoya.base.infrastructure.acl.dto.InsertAccountRequestDto;

public class RegisterAccountRequestDtoTranslator {

  private RegisterAccountRequestDtoTranslator() {
    throw new IllegalStateException("Utility class");
  }

  public static RegisterAccount of(InsertAccountRequestDto requestDto) {
    return new RegisterAccount(
        requestDto.email(),
        requestDto.cellphones()
    );
  }

}
