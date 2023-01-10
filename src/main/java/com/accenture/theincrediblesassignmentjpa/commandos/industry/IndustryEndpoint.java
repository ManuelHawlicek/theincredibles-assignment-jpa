package com.accenture.theincrediblesassignmentjpa.commandos.industry;

import com.accenture.theincrediblesassignmentjpa.models.Industry;
import com.accenture.theincrediblesassignmentjpa.models.Stock;
import com.accenture.theincrediblesassignmentjpa.models.repositories.IndustryRepository;
import com.accenture.theincrediblesassignmentjpa.service.IndustryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industry")
public class IndustryEndpoint {

    private final IndustryRepository industryRepository;
    private final IndustryService industryService;

    public IndustryEndpoint(IndustryRepository industryRepository, IndustryService industryService) {
        this.industryRepository = industryRepository;
        this.industryService = industryService;
    }

    @GetMapping("/list")
    List<Industry> getIndustries() {
        return industryRepository.findAll();
    }

    @PutMapping("/update-industry/{id}/{name}")
    Stock updateIndustry(@PathVariable Long id, @PathVariable String name) {
        return industryService.updateIndustry(id, name);
    }
}
