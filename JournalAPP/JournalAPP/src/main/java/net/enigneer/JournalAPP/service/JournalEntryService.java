package net.enigneer.JournalAPP.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.enigneer.JournalAPP.entity.JournalEntry;
import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.repository.JournalEntryRepo;

@Service
public class JournalEntryService {
    

    @Autowired
    private JournalEntryRepo JournalEntryrepo;

    @Autowired
    private UserService Userservice;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){

        try{
                User user=Userservice.findByUserName(userName);
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved=JournalEntryrepo.save(journalEntry);
                user.getJournalEntries().add(saved);
                user.setUsername(null);
                Userservice.saveEntry(user);
        }
        catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("an error occured while saving the entry",e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        
        JournalEntryrepo.save(journalEntry);
    }
    

    public List<JournalEntry> getAll(){
        return JournalEntryrepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryrepo.findById(id);
    }

    public void deleteById(ObjectId id,String userName){
        User user=Userservice.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        Userservice.saveEntry(user);
        JournalEntryrepo.deleteById(id);
    }

    // public JournalEntry updatebyId(ObjectId id,JournalEntry newEntry){
        
    // }
    

}


//controller-->service-->repository
