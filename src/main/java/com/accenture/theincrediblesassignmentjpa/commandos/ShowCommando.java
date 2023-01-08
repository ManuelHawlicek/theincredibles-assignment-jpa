package com.accenture.theincrediblesassignmentjpa.commandos;

import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ShowCommando implements Commando{

    private Scanner scanner;
    private StockRepository stockRepository;

    public ShowCommando(Scanner scanner, StockRepository stockRepository) {
        this.scanner = scanner;
        this.stockRepository = stockRepository;
    }


    @Override
    public boolean execute() {
        return false;
    }


    @Override
    public boolean shouldExecute(String userCommando) {
        return "show".startsWith(userCommando);
    }
}
