package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Company;
import com.arty.simpleCRUD.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController
{
    @Autowired
    private CompanyRepository repo;

    @GetMapping("/company")
    public List<Company> getCompanies() {
        try {
            List<Company> l = repo.findAll();
            return l;
        } catch (Exception ex) {
          return null;
        }
    }

}
