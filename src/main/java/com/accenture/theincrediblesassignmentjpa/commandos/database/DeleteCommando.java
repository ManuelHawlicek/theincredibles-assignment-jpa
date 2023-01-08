package com.accenture.theincrediblesassignmentjpa.commandos.database;

import com.accenture.theincrediblesassignmentjpa.commandos.Commando;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.models.repositories.StockRepository;
import com.accenture.theincrediblesassignmentjpa.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class DeleteCommando implements Commando {

    StockRepository stockRepository;
    CompanyRepository companyRepository;
    IndustryRepository industryRepository;

    public DeleteCommando(StockRepository stockRepository, CompanyRepository companyRepository, IndustryRepository industryRepository) {
        this.stockRepository = stockRepository;
        this.companyRepository = companyRepository;
        this.industryRepository = industryRepository;
    }

    @Override
    public boolean execute() {
        stockRepository.deleteAll();
        stockRepository.resetAutoIncrement();
        companyRepository.deleteAll();
        companyRepository.resetAutoIncrement();
        industryRepository.deleteAll();
        industryRepository.resetAutoIncrement();
        System.out.println("All Data has been deleted.");
        return true;
    }

    @Override
    public boolean shouldExecute(String commando) {
        return "delete".equals(commando);
    }

}
