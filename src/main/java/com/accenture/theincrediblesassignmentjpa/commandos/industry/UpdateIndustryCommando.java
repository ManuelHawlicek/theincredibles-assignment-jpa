package com.accenture.theincrediblesassignmentjpa.commandos.industry;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;
import java.util.List;

public class UpdateIndustryCommando implements CommandoWithParams {

    private StockService stockService;

    public UpdateIndustryCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        List<String> userInputSplit = List.of(userInput.split(" ", 3));
        try {
            String id = userInputSplit.get(1);
            String industryName = userInputSplit.get(2);
            stockService.updateIndustryInStock(id, industryName);
            System.out.println("hier");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "update-industry".equals(commando);
    }
}
