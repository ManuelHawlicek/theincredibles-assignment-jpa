package com.accenture.theincrediblesassignmentjpa.commandos;

public class AddCommando implements Commando{
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return false;
    }
}
