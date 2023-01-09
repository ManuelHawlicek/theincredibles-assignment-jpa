package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;
import java.util.List;

public class GapCommando implements CommandoWithParams {

    private StockService stockService;

    public GapCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        try {
            stockService.showStockGap(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "gap".equals(commando);
    }
}
