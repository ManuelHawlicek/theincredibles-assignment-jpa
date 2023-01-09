package com.accenture.theincrediblesassignmentjpa.commandos.company;

import com.accenture.theincrediblesassignmentjpa.models.Company;
import com.accenture.theincrediblesassignmentjpa.models.repositories.CompanyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyEndpoint {

    private final CompanyRepository companyRepository;

    public CompanyEndpoint(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{input}")
    List<Company> getCompanies(@PathVariable String input) {
        return companyRepository.findByNameContaining(input);
    }
}
