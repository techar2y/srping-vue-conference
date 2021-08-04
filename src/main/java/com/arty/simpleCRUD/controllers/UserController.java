package com.arty.simpleCRUD.controllers;

import com.arty.simpleCRUD.domains.User;
import com.arty.simpleCRUD.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> getAllUsers(@RequestParam(required = false) String searchStr,
                                                           @RequestParam(defaultValue="0") int page,
                                                           @RequestParam(defaultValue="3") int pageSize){
        try {
            Pageable paging = PageRequest.of(page, pageSize);

            Page<User> users = null;
            if (searchStr == null || searchStr.length() == 0)
                users = userRepository.findAll(paging);
            else
                users = userRepository.findUserByLoginContaining(searchStr, paging);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("users", users.getContent());
            response.put("currentPage", users.getTotalPages());
            response.put("totalItems", users.getTotalElements());
            response.put("totalPages", users.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<User> deleteAllUsers(){
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            User _user = userRepository.save(user);
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        try {
            User user = userRepository.getUserById(id);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Long id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        try {
            User newUser = userRepository.getUserById(id);
            if(newUser == null)
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


            if (user != null) {
                newUser.setFullName(user.getFullName());
                newUser.setEmail(user.getEmail());
                newUser.setLogin(user.getLogin());
                newUser.setRole(user.getRole());
                newUser = userRepository.save(newUser);
            }

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/isLoginUnique")
    public ResponseEntity<Integer> isLoginUnique(@RequestParam String login, @RequestParam Long id) {
        try {
            List<User> users = userRepository.findUsersByLoginAndIdNot(login, id);

            return new ResponseEntity<>(users.size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/isEmailUnique")
    public ResponseEntity<Integer> isEmailUnique(@RequestParam String email, @RequestParam Long id) {
        try {
            List<User> users = userRepository.findUsersByEmailAndIdNot(email, id);

            return new ResponseEntity<>(users.size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
