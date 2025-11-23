package net.enigneer.JournalAPP.controller;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.enigneer.JournalAPP.entity.JournalEntry;

import net.enigneer.JournalAPP.service.JournalEntryService;



@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

   @Autowired
   private JournalEntryService JournalEntryservice;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all=JournalEntryservice.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping    
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry){
        try{
            myentry.setDate(LocalDateTime.now());
            JournalEntryservice.saveEntry(myentry);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry=JournalEntryservice.findById(myid);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> putJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
        
        JournalEntry oldEntry=JournalEntryservice.findById(id).orElse(null);
        if(oldEntry!=null){
            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            JournalEntryservice.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id){
        JournalEntryservice.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

