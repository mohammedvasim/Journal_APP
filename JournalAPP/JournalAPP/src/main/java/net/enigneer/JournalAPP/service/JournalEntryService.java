package net.enigneer.JournalAPP.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.enigneer.JournalAPP.entity.JournalEntry;
import net.enigneer.JournalAPP.repository.JournalEntryRepo;

@Service
public class JournalEntryService {
    

    @Autowired
    private JournalEntryRepo JournalEntryrepo;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        return JournalEntryrepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return JournalEntryrepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryrepo.findById(id);
    }

    public boolean deleteById(ObjectId id){
        JournalEntryrepo.deleteById(id);
        return true;
    }

    // public JournalEntry updatebyId(ObjectId id,JournalEntry newEntry){
        
    // }
    

}


//controller-->service-->repository
