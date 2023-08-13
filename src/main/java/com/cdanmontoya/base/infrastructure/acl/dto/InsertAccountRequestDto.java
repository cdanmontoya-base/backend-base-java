package com.cdanmontoya.base.infrastructure.acl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;


public record InsertAccountRequestDto(
    @Email
    @NotBlank
    String email,

    @NotEmpty
    List<String> cellphones
) {

}
