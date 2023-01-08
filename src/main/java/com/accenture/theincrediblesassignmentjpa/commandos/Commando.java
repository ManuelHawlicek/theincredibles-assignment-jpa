package com.accenture.theincrediblesassignmentjpa.commandos;

import java.sql.SQLException;

public interface Commando {
    boolean execute() throws SQLException;

    boolean shouldExecute(String commando);

}
