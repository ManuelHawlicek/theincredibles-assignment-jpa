package com.accenture.theincrediblesassignmentjpa.commandos.stock;

import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockEndpoint {

    private final StockService stockService;

    public StockEndpoint(StockService stockService) {
        this.stockService = stockService;
    }

//    @GetMapping("/add/{id}/{date}/{price}")
//    Stock addStock(@PathVariable Long id, LocalDate date, Double price) {
//        String test = "add" + " " + id + " " + date + " " + price;
//        return stockService.addStock(test);
//    }

//    @GetMapping("/show/{id}")
//    List<Industry> getIndustries() {
//        return industryRepository.findAll();
//    }
//
    @GetMapping("/low/{id}")
    String getLowestPrice(@PathVariable String id) {
        String userInput = "low " + id;
        Double price = stockService.showStockLowestPrice(userInput);
        return "The lowest price for the specified Stock with ID " + id + " was: " + price;
    }
    @GetMapping("/max/{id}")
    String getHighestPrice(@PathVariable String id) {
        String userInput = "max " + id;
        Double price = stockService.showStockHighestPrice(userInput);
        return "The highest price for the specified Stock with ID " + id + " was: " + price;
    }

    @GetMapping("/gap/{id}")
    String getGapBetweenStocks(@PathVariable String id) {
        String userInput = "gap " + id;
        Double gap = stockService.showStockGap(userInput);
        if (!(gap == 0.0)) {
            return "The biggest difference in prices for the specified Stock with ID " + id + " was: " + gap;
        }
        return "We only have one saved Stock in our Database, so there is no gap";
    }

}
