package com.accenture.theincrediblesassignmentjpa.service;

import com.accenture.theincrediblesassignmentjpa.models.Company;
import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.utils.FileReader;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

    public void updateIndustryInStock(String id, String industryName) {
        try {
            long stockId = Long.parseLong(id);
            Optional<Stock> stock = stockRepository.findById(stockId);
            System.out.println("hier1");
            if (stock.isPresent()) {
                Optional<Industry> industry = industryRepository.findFirstByName(industryName);
                System.out.println("hier2");
                if (industry.isPresent()) {
                    stock.get().setIndustry(industry.get());
                    System.out.println("hier3");
                    stockRepository.save(stock.get());
                }
            }

        }catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
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

    public void showStockLastTenPrices(String userInput) {
        List<String> userInputSplit = List.of(userInput.split(" ", -1));
        try {
            long companyId = Long.parseLong(userInputSplit.get(1));
            List<Stock> stockList = stockRepository.findAllByCompanyId(companyId);
            System.out.println("The last prices for the specified Stock with ID: " + companyId + " are: ");
            stockList.stream()
                    .map(e -> e.getPrice())
                    .limit(10)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Double showStockHighestPrice(String userInput) {
        List<String> userInputSplit = List.of(userInput.split(" ", -1));
        try {
            long companyId = Long.parseLong(userInputSplit.get(1));
            List<Stock> stockList = stockRepository.findAllByCompanyId(companyId);
            Optional<Double> oDouble = stockList.stream()
                    .map(e -> e.getPrice())
                    .max(Comparator.comparing(Double::doubleValue));
            if (oDouble.isPresent()) {
                System.out.println("The highest price for the specified Stock with ID " + companyId + " is: " + oDouble.get());
                return oDouble.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double showStockLowestPrice(String userInput) {
        List<String> userInputSplit = List.of(userInput.split(" ", -1));
        try {
            long companyId = Long.parseLong(userInputSplit.get(1));
            List<Stock> stockList = stockRepository.findAllByCompanyId(companyId);
            Optional<Double> oDouble = stockList.stream()
                    .map(e -> e.getPrice())
                    .min(Comparator.comparing(Double::doubleValue));
            if (oDouble.isPresent()) {
                System.out.println("The lowest price for the specified Stock with ID " + companyId + " is: " + oDouble.get());
                return oDouble.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Double showStockGap(String userInput) {
        List<String> userInputSplit = List.of(userInput.split(" ", -1));
        try {
            long companyId = Long.parseLong(userInputSplit.get(1));
            List<Stock> stockList = stockRepository.findAllByCompanyId(companyId);
            Optional<Double> oDoubleLowest = stockList.stream()
                    .map(e -> e.getPrice())
                    .min(Comparator.comparing(Double::doubleValue));

            Optional<Double> oDoubleHighest = stockList.stream()
                    .map(e -> e.getPrice())
                    .max(Comparator.comparing(Double::doubleValue));
            if (oDoubleHighest.isPresent() && oDoubleLowest.isPresent()) {
                Double priceGap = oDoubleHighest.get() - oDoubleLowest.get();
                if (!(priceGap == 0.0)) {
                    System.out.println("The biggest difference in prices for the specified Stock with ID " + companyId + " was: " + priceGap);
                    return priceGap;
                } else {
                    System.out.println("We only have one saved Stock in our Database, so there is no gap");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Stock addStock(String userInput) {
        List<String> userInputSplit = List.of(userInput.split(" ", -1));
        long stockId = Long.parseLong(userInputSplit.get(1));
        Optional<Stock> oStock = stockRepository.findById(stockId);
        double price = Double.parseDouble(userInputSplit.get(3).replace(",", "."));
        LocalDate date = LocalDate.parse(userInputSplit.get(2), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if (oStock.isPresent()) {
            Stock stock = oStock.get();
            Stock addStock = new Stock(stock.getCompanyName(),
                    stock.getCompany(),
                    price,
                    date,
                    stock.getIndustry() );
            stockRepository.save(addStock);
            System.out.println("New Stock has been saved.");
            return addStock;
        }
        return null;
    }

    public void deleteAllData() {
        stockRepository.deleteAll();
        stockRepository.resetAutoIncrement();
        companyRepository.deleteAll();
        companyRepository.resetAutoIncrement();
        industryRepository.deleteAll();
        industryRepository.resetAutoIncrement();
    }
}
