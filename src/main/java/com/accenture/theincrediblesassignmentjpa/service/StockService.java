package com.accenture.theincrediblesassignmentjpa.service;

import com.accenture.theincrediblesassignmentjpa.models.Company;
import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.utils.FileReader;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    StockRepository stockRepository;
    CompanyRepository companyRepository;
    IndustryRepository industryRepository;

    public StockService(StockRepository stockRepository, CompanyRepository companyRepository, IndustryRepository industryRepository) {
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.industryRepository = industryRepository;
    }

    public void importCompanyIndustryData() {
        String path = "src/main/resources/STOCK_DATA.csv";
        FileReader fileReader = new FileReader();
        List<String> file = fileReader.read(path);
        file.remove(0);
        for (String line : file) {
            try {
                List<String> lineSplit = List.of(line.split(";", -1));

                String companyName = lineSplit.get(0);
                String industryName = lineSplit.get(3);
                Company company = new Company(companyName);
                Industry industry = new Industry(industryName);
                if (!companyRepository.existsByName(company.getName())) {
                    companyRepository.save(company);
                }
                if (!industryRepository.existsByName(industry.getName())) {
                    industryRepository.save(industry);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        importStockData();
    }

    private void importStockData() {
        String path = "src/main/resources/STOCK_DATA.csv";
        FileReader fileReader = new FileReader();
        List<String> file = fileReader.read(path);
        file.remove(0);
        for (String line : file) {

            List<String> lineSplit = List.of(line.split(";", -1));
            String companyName = lineSplit.get(0);
            String industryName = lineSplit.get(3);
            Long companyId = 0L;
            Long industryId = 0L;

            Optional<Company> oCompany = companyRepository.findFirstByName(companyName);
            Optional<Industry> oIndustry = industryRepository.findFirstByName(industryName);

            String priceWithComma = lineSplit.get(1);
            String priceWithEuro = priceWithComma.replace(",", ".");
            double price = Double.parseDouble(priceWithEuro.replace("€", ""));
            LocalDate date = LocalDate.parse(lineSplit.get(2), DateTimeFormatter.ofPattern("dd.MM.yy"));

            Stock stock = new Stock(companyName, oCompany.get(), price, date, oIndustry.get());
            stockRepository.save(stock);
        }
    }
}
