package com.accenture.theincrediblesassignmentjpa.commandos.database;

import com.accenture.theincrediblesassignmentjpa.commandos.Commando;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;

import java.sql.SQLException;

public class ImportCommando implements Commando {

    private StockRepository stockRepository;
    private StockService stockService;

    public ImportCommando(StockRepository stockRepository, StockService stockService) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    @Override
    public boolean execute() throws SQLException {
        System.out.println("Importing data will take a few moments - please wait until the data has been imported.");
        stockService.importCompanyIndustryData();
        System.out.println("Data from .csv file has been imported.");
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "import".equals(commando);
    }

}
