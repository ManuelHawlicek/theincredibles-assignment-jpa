package com.accenture.theincrediblesassignmentjpa;

import com.accenture.theincrediblesassignmentjpa.commandos.*;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TheincrediblesAssignmentJpaApplication implements CommandLineRunner {
    private final IndustryRepository industryRepository;
    private final StockRepository stockRepository;
    private final CompanyRepository companyRepository;
    private final StockService stockService;
    private final CommandoListFactory factory;

    public TheincrediblesAssignmentJpaApplication(IndustryRepository industryRepository, StockRepository stockRepository,
                                                  CompanyRepository companyRepository, StockService stockService, CommandoListFactory factory) {
        this.industryRepository = industryRepository;
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.stockService = stockService;
        this.factory = factory;
    }

    public static void main(String[] args) {
        SpringApplication.run(TheincrediblesAssignmentJpaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<Commando> commandos = factory.loadCommandoList();
        List<CommandoWithParams> commandosWithParameters = factory.loadCommandoWithParamsList();

        System.out.println("Welcome to the International Stock Analysis-App");

        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("Hello User, what would you like to do?");

            String userCommando = scanner.nextLine();
            System.out.println("You wrote: " + userCommando);

            List<String> userCommandoSplit = List.of(userCommando.split(" ", -1));
            if (userCommandoSplit.size() > 1) {
                for (CommandoWithParams commandoWithParams : commandosWithParameters) {
                    if (commandoWithParams.shouldExecute(userCommandoSplit.get(0))) {
                        shouldRun = commandoWithParams.execute(userCommando);
                    }
                }

            } else {
                for (Commando commando : commandos) {
                    if (commando.shouldExecute(userCommando)) {
                        shouldRun = commando.execute();
                    }
                }
            }
        }
    }
}
