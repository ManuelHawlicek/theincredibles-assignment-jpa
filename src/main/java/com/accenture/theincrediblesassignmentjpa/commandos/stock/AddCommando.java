package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;

public class AddCommando implements CommandoWithParams {
    private StockService stockService;

    public AddCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        stockService.addStock(userInput);
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "add".equals(commando);
    }
}
