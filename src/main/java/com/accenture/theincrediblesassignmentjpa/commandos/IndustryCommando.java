package com.accenture.theincrediblesassignmentjpa.commandos;

import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;

import java.util.List;
import java.util.Scanner;

public class IndustryCommando implements Commando{

    private Scanner scanner;
    private StockRepository stockRepository;
    private IndustryRepository industryRepository;

    public IndustryCommando(Scanner scanner, StockRepository stockRepository, IndustryRepository industryRepository) {
        this.scanner = scanner;
        this.stockRepository = stockRepository;
        this.industryRepository = industryRepository;
    }

    @Override
    public boolean execute() {
        List<Industry> industryList = industryRepository.findAll();
        for (Industry industry : industryList) {
            System.out.println("Name" + industry.getName() + "ID" + industry.getId() + "Stocks");
        }
        return false;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "industries".equals(userCommando);
    }
}
