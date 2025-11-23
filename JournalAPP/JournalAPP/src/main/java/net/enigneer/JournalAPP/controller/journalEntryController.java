// package net.enigneer.JournalAPP.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import net.enigneer.JournalAPP.entity.JournalEntry;

// @RestController
// @RequestMapping("/journal")
// public class journalEntryController {

//     private Map<Long,JournalEntry> journalentries=new HashMap<>();

//     @GetMapping
//     public List<JournalEntry> getAll(){
//         return new ArrayList<>(journalentries.values());
//     }

//     @PostMapping
//     public boolean createEntry(@RequestBody JournalEntry entry){
//         journalentries.put(entry.getId(),entry);
//         return true;
//     }
    
//     @GetMapping("id/{myid}")
//     public JournalEntry getJournalEntryById(@PathVariable Long myid){
//         return journalentries.get(myid);
//     }

//     @PutMapping("id/{id}")
//     public JournalEntry putJournalEntryById(@PathVariable Long id,@RequestBody JournalEntry newEntry){
//         return journalentries.put(id,newEntry);
//     }
    
//     @DeleteMapping("id/{id}")
//     public JournalEntry deleteJournalById(@PathVariable Long id){
//         return journalentries.remove(id);
//     }
// }
