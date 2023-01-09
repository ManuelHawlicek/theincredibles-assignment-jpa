package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;

public class MaxCommando implements CommandoWithParams {

    private StockService stockService;

    public MaxCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        stockService.showStockHighestPrice(userInput);
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "max".equals(commando);
    }
}
