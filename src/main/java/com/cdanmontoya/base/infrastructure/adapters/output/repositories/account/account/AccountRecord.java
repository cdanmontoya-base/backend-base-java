package com.cdanmontoya.base.infrastructure.adapters.output.repositories.account.account;

import java.util.List;
import java.util.UUID;

public record AccountRecord(
    UUID id,
    String email,
    List<String> cellphones
) {

}
