package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Branch;
import com.arty.simpleCRUD.repos.BranchRepository;
import com.arty.simpleCRUD.utils.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BranchController
{
    @Autowired
    private BranchRepository branchRepository;


    @Autowired
    private BranchService service;

    @GetMapping("/branch")
    public List<Branch> getBranches() {

        List<Branch> lB = branchRepository.findAll();
        return lB;
    }
}
