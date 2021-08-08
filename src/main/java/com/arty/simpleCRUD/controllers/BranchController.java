package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Branch;
import com.arty.simpleCRUD.repos.BranchRepository;
import com.arty.simpleCRUD.utils.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BranchController
{
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchService service;

    /*@PersistenceContext
    private EntityManager entityManager;*/

    @GetMapping("/branch")
    public List<Branch> getBranches (@RequestParam String name)
    {
        try
        {
            /*Query q = entityManager.createNativeQuery("SELECT * FROM branches b JOIN companies c ON b.company_id = c.id WHERE c.name = :name");
            q.setParameter("name", name);*/
            List<Branch> lB = branchRepository.findBranchByCompaniesName(name);
            return lB;
        } catch (Exception e)
        {
            return null;
        }
    }
}
