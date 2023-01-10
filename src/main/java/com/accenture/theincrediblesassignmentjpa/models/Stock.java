package com.accenture.theincrediblesassignmentjpa.models;

import com.accenture.theincrediblesassignmentjpa.models.Company;
import com.accenture.theincrediblesassignmentjpa.models.Industry;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "companyName", length = 100)
    private String companyName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "price")
    private Double price;

    @Column(name = "dateofprice")
    private LocalDate dateofprice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    public Stock() {
    }

    public Stock(String companyName, Company company, Double price, LocalDate dateofprice, Industry industry) {
        this.companyName = companyName;
        this.company = company;
        this.price = price;
        this.dateofprice = dateofprice;
        this.industry = industry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDateofprice() {
        return dateofprice;
    }

    public void setDateofprice(LocalDate dateofprice) {
        this.dateofprice = dateofprice;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

}