package com.accenture.theincrediblesassignmentjpa.commandos;

public class ExitCommando implements Commando{
    @Override
    public boolean execute() {
        System.out.println("Thank you! Bye! :)");
        return false;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "exit".equals(userCommando);
    }
}
