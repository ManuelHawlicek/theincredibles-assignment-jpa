package com.accenture.theincrediblesassignmentjpa.commandos.industry;

import com.accenture.theincrediblesassignmentjpa.commandos.Commando;
import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;

import java.util.List;
import java.util.Scanner;

public class IndustryCommando implements Commando {

    private IndustryRepository industryRepository;

    public IndustryCommando(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public boolean execute() {
        List<Industry> industryList = industryRepository.findAll();
        for (Industry industry : industryList) {
            int count = industryRepository.countIndustries(industry.getId());
            System.out.println("Industry ID: " + industry.getId() + " Industry Name: " + industry.getName() + " Stocks assigned: " + count);
        }
        return false;
    }

    @Override
    public boolean shouldExecute(String userCommando) {
        return "industries".equals(userCommando);
    }
}
