package com.arty.simpleCRUD.utils;

import com.arty.simpleCRUD.domains.Branch;
import com.arty.simpleCRUD.repos.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService
{
    @Autowired
    private BranchRepository repo;

    public List<Branch> getBranches() {
        return (List<Branch>) repo.findAll();
    }
}
