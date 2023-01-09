package com.accenture.theincrediblesassignmentjpa;

import com.accenture.theincrediblesassignmentjpa.commandos.*;
import com.accenture.theincrediblesassignmentjpa.commandos.company.SearchCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.database.DeleteCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.database.ImportCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.industry.IndustryCommando;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TheincrediblesAssignmentJpaApplication implements CommandLineRunner {
    private final IndustryRepository industryRepository;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;
    private final StockService stockService;

    public TheincrediblesAssignmentJpaApplication(IndustryRepository industryRepository, StockRepository stockRepository,
                                                  CompanyRepository companyRepository, StockService stockService) {
        this.industryRepository = industryRepository;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.stockService = stockService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TheincrediblesAssignmentJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Commando importCommando = new ImportCommando(stockRepository, stockService);
        Commando deleteCommando = new DeleteCommando(stockRepository, companyRepository, industryRepository);
        Commando searchCommando = new SearchCommando(companyRepository, scanner);
        Commando showCommando = new ShowCommando(scanner, stockRepository);
//        Commando addCommando = new AddCommando();
//        Commando maxCommando = new MaxCommando();
//        Commando lowCommando = new LowCommando();
//        Commando gapCommando = new GapCommando();
//        Commando updateIndustryCommando = new UpdateIndustryCommando();
        Commando industriesCommando = new IndustryCommando(industryRepository);
        Commando exitCommando = new ExitCommando();

        List<Commando> commandos = new ArrayList<>();
        commandos.add(importCommando);
        commandos.add(deleteCommando);
        commandos.add(searchCommando);
        commandos.add(showCommando);
        commandos.add(industriesCommando);
        commandos.add(exitCommando);

        boolean shouldRun = true;
        while(shouldRun) {
            System.out.println("Hello User! Thank you for using our Service. What Command would you like to use?");

            String userCommando = scanner.nextLine();
            System.out.println("You wrote: " + userCommando);

            for (Commando commando : commandos) {
//                if (userCommando.startsWith("show")) {
//                    List<String> stringSplit = List.of(userCommando.split(" "));
//                    shouldRun = commando.executeWithParameter(stringSplit.get(1));
//                }
                if (commando.shouldExecute(userCommando)) {
                    shouldRun = commando.execute();
                }
            }
        }

    }
}
