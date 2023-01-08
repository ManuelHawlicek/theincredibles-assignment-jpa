package com.accenture.theincrediblesassignmentjpa.commandos.company;

import com.accenture.theincrediblesassignmentjpa.commandos.Commando;
import com.accenture.theincrediblesassignmentjpa.models.Company;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;

import java.util.List;
import java.util.Scanner;

public class SearchCommando implements Commando {

    private CompanyRepository companyRepository;
    private Scanner scanner;

    public SearchCommando(CompanyRepository companyRepository, Scanner scanner) {
        this.companyRepository = companyRepository;
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("Please enter the first Characters of the Company");
        String companyName = scanner.nextLine();

        List<Company> companyList = companyRepository.findByNameContaining(companyName);
        if (companyList.isEmpty()) {
            System.out.println("Sorry, we found no entries.");
        } else {
            System.out.println("I found following Companies with the name you specified: ");
            for (Company company : companyList) {
                System.out.println("Company Name: " + company.getName() + " with Company ID: " + company.getId());
            }
        }
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "search".equals(commando);
    }

}
