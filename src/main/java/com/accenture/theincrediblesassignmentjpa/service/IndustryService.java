package com.accenture.theincrediblesassignmentjpa.service;

import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndustryService {

    private final StockRepository stockRepository;
    private final IndustryRepository industryRepository;

    public IndustryService(StockRepository stockRepository, IndustryRepository industryRepository) {
        this.stockRepository = stockRepository;
        this.industryRepository = industryRepository;
    }

    public Stock updateIndustry(Long id, String name) {
        Optional<Industry> oIndustry = industryRepository.findFirstByName(name);
        Optional<Stock> oStock = stockRepository.findById(id);
        if (oIndustry.isPresent() && oStock.isPresent()) {
            Industry industry = oIndustry.get();
            Stock stock = oStock.get();
            stock.setIndustry(industry);
            stockRepository.save(stock);
            return stock;

        }
        return null;
    }


}
