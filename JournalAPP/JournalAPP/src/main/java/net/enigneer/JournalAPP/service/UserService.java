package net.enigneer.JournalAPP.service;

import java.io.ObjectInput;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo Userrepo;

    public void saveEntry(User user){
        Userrepo.save(user);
    }

    public List<User> getAll(){
        return Userrepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return Userrepo.findById(id);
    }

    public void deleteById(ObjectId id){
        Userrepo.deleteById(id);
    }
   
    public User findByUserName(String username){
        return Userrepo.findByUsername(username);
    }
}
