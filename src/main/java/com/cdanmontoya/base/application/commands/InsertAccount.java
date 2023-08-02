package com.cdanmontoya.base.application.commands;

import com.cdanmontoya.ddd.Command;
import io.vavr.collection.List;

public record InsertAccount(
    String email,
    List<String> cellphones
) implements Command {

}
