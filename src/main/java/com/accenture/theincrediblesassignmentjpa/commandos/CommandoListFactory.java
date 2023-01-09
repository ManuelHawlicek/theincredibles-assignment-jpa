package com.accenture.theincrediblesassignmentjpa.commandos;

import com.accenture.theincrediblesassignmentjpa.commandos.company.SearchCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.database.DeleteCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.database.ImportCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.industry.IndustryCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.industry.UpdateIndustryCommando;
import com.accenture.theincrediblesassignmentjpa.commandos.stock.*;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.service.StockService;
import jakarta.persistence.SequenceGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class CommandoListFactory {

    private StockRepository stockRepository;
    private StockService stockService;
    private IndustryRepository industryRepository;
    private CompanyRepository companyRepository;

    public CommandoListFactory(StockRepository stockRepository, StockService stockService, IndustryRepository industryRepository, CompanyRepository companyRepository) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.industryRepository = industryRepository;
        this.companyRepository = companyRepository;
    }

    public List<Commando> loadCommandoList() {
        Scanner scanner = new Scanner(System.in);
        List<Commando> commandos = new ArrayList<>();
        commandos.add(new ExitCommando());
        commandos.add(new ImportCommando(stockService));
        commandos.add(new DeleteCommando(stockService));
        commandos.add(new SearchCommando(companyRepository, scanner));
        commandos.add(new IndustryCommando(industryRepository));
//        commandos.add(new ExportCommando(stockRepository));

        return commandos;

    }

    public List<CommandoWithParams> loadCommandoWithParamsList() {
        List<CommandoWithParams> commandosWithParams = new ArrayList<>();
        commandosWithParams.add(new ShowCommando(stockService));
        commandosWithParams.add(new AddCommando(stockService));
        commandosWithParams.add(new MaxCommando(stockService));
        commandosWithParams.add(new LowCommando(stockService));
        commandosWithParams.add(new GapCommando(stockService));
        commandosWithParams.add(new UpdateIndustryCommando(stockService));

        return commandosWithParams;

    }
}
