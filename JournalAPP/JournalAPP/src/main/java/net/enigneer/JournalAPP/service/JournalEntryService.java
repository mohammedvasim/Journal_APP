package net.enigneer.JournalAPP.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
                //user.setUsername(null); // REMOVED: user.setUsername(null); ❌ This was causing the error!
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

    @Transactional  
    public boolean deleteById(ObjectId id,String userName){
        boolean removed=false;
        try{
            User user=Userservice.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            
            if(removed){
                Userservice.saveEntry(user);
                JournalEntryrepo.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry:",e);
        }
        return removed;
    }

    // public JournalEntry updatebyId(ObjectId id,JournalEntry newEntry){
        
    // }

    // public List<JournalEntry> findByUsername(String username){
    //     User user=Userservice.findByUserName(username);
    //     return user.getJournalEntries();
    // }
    

}


//controller-->service-->repository
