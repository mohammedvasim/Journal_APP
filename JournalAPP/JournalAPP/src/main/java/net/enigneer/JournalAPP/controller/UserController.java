package net.enigneer.JournalAPP.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService Userservice;

    @GetMapping
    public List<User> getAllUser(){
        return Userservice.getAll();
    }
    
    @PostMapping
    public void createUser(@RequestBody User user){
        Userservice.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User userInDb=Userservice.findByUserName(username);
        if(userInDb!=null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            Userservice.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

