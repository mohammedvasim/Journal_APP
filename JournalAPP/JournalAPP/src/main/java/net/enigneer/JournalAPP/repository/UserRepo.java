package net.enigneer.JournalAPP.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.enigneer.JournalAPP.entity.User;

@Repository
public interface UserRepo extends MongoRepository<User,ObjectId>{

    User findByUsername(String username);
}
