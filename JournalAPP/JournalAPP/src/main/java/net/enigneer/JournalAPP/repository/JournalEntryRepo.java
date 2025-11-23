package net.enigneer.JournalAPP.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.enigneer.JournalAPP.entity.JournalEntry;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry,ObjectId> {
    
}
