package net.enigneer.JournalAPP.controller;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.enigneer.JournalAPP.entity.JournalEntry;
import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.service.JournalEntryService;
import net.enigneer.JournalAPP.service.UserService;



@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

   @Autowired
   private JournalEntryService JournalEntryservice;

   @Autowired
   private UserService Userservice;

    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUsers(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=Userservice.findByUserName(username);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myentry){
        try{
            Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            JournalEntryservice.saveEntry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=Userservice.findByUserName(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=JournalEntryservice.findById(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> putJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
        
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=Userservice.findByUserName(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=JournalEntryservice.findById(id);
            if(journalEntry.isPresent()){
                JournalEntry old=journalEntry.get();
                old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                JournalEntryservice.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId id){
        // User user=Userservice.findByUserName(userName)  /forgot why i used it

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        boolean removed=JournalEntryservice.deleteById(id, username);
        if(removed){
            JournalEntryservice.deleteById(id,username);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

