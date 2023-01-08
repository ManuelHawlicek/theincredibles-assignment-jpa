package com.accenture.theincrediblesassignmentjpa.commandos;

import java.sql.SQLException;

public interface CommandoWithParams {

    boolean execute(String userInput) throws SQLException;

    boolean shouldExecute(String commando);
}
