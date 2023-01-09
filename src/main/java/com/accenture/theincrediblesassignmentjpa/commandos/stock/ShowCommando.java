package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.commandos.CommandoWithParams;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;

public class ShowCommando implements CommandoWithParams {

    private StockService stockService;

    public ShowCommando(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public boolean execute(String userInput) throws SQLException {
        try {
            stockService.showStockLastTenPrices(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "show".equals(commando);
    }

}
