package com.cdanmontoya.base.infrastructure.acl.dto;

import com.cdanmontoya.base.domain.model.Account;
import java.util.List;

public record QueryAccountsResponseDto (
    List<Account> accounts
) {

}
