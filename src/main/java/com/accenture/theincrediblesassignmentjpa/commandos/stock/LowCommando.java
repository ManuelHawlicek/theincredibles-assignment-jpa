package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;

public class LowCommando implements CommandoWithParams {

    private StockService stockService;

    public LowCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        stockService.showStockLowestPrice(userInput);
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "low".equals(commando);
    }
}
