package net.enigneer.JournalAPP.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.repository.UserRepo;
import net.enigneer.JournalAPP.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    // @GetMapping
    // public List<User> getAllUser(){
    //     return Userservice.getAll();
    // }
    


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDb=userService.findByUserName(username);
        if(userInDb!=null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

