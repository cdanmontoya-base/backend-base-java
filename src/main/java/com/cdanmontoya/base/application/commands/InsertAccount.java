package com.cdanmontoya.base.application.commands;

import com.cdanmontoya.ddd.Command;
import java.util.List;

public record InsertAccount(
    String email,
    List<String> cellphones
) implements Command {

}
