package com.accenture.theincrediblesassignmentjpa.commandos;

import com.accenture.theincrediblesassignmentjpa.commandos.company.SearchCommando;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandoListFactory {

    private StockRepository stockRepository;
    private IndustryRepository industryRepository;
    private CompanyRepository companyRepository;

    public CommandoListFactory(StockRepository stockRepository, IndustryRepository industryRepository, CompanyRepository companyRepository) {
        this.stockRepository = stockRepository;
        this.industryRepository = industryRepository;
        this.companyRepository = companyRepository;
    }

    public List<Commando> loadCommandoList() {
        Scanner scanner = new Scanner(System.in);
        List<Commando> commandos = new ArrayList<>();
        commandos.add(new ExitCommando());
//        commandos.add(new ImportCommando(stockRepository));
//        commandos.add(new DeleteCommando(stockRepository));
        commandos.add(new SearchCommando(companyRepository, scanner));
//        commandos.add(new IndustryCommando(industryRepository));
//        commandos.add(new ExportCommando(stockRepository));

        return commandos;

    }

//    public List<CommandoWithParams> loadCommandoWithParamsList() {
//        List<CommandoWithParams> commandosWithParams = new ArrayList<>();
//        commandosWithParams.add(new ShowCommando(stockRepository));
//        commandosWithParams.add(new AddCommando(stockRepository, industryRepository));
//        commandosWithParams.add(new MaxCommando(stockRepository));
//        commandosWithParams.add(new LowCommando(stockRepository));
//        commandosWithParams.add(new GapCommando(stockRepository));
//        commandosWithParams.add(new UpdateIndustryCommando(stockRepository, industryRepository));

//        return commandosWithParams;

//    }
}
