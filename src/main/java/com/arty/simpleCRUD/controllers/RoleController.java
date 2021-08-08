package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.Role;
import com.arty.simpleCRUD.repos.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RoleController
{
    @Autowired
    public IRoleRepository roleRepository;

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        try {
            List<Role> roles = roleRepository.findAll();

            if(roles.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }


            return new ResponseEntity<>(roles, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
