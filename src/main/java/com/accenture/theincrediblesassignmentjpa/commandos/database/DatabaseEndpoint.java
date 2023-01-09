package com.accenture.theincrediblesassignmentjpa.commandos.database;

import com.accenture.theincrediblesassignmentjpa.service.StockService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseEndpoint {

    private final StockService stockService;

    public DatabaseEndpoint(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/import")
    String importData() {
        stockService.importCompanyIndustryData();
        return "Data has been imported";
    }

    @DeleteMapping("delete")
    String deleteAllData() {
        stockService.deleteAllData();
        return "Data has been deleted.";
    }
}
