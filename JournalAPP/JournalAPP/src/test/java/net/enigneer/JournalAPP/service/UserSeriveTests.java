package net.enigneer.JournalAPP.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.repository.UserRepo;

@SpringBootTest
public class UserSeriveTests {
    
    // @Test
    // public void testAdd(){
    //     assertEquals(4, 2+2);
    // }

    @Autowired
    private UserRepo userRepo;



    @ParameterizedTest
    @CsvSource({
        "vasim",
        "anisa",
        "shayam"
    })
    public void findByUsername(String name){
        //assertEquals(4, 2+2);
        // assertNotNull(userRepo.findByUsername("vasim"));
        // assertTrue(5>3);

    //    User user =userRepo.findByUsername("vasim");
    //    assertTrue(!user.getJournalEntries().isEmpty());

        assertNotNull(userRepo.findByUsername(name),"Failed for:" + name);

    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,10,12",
        "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected, a+b);
    }

}
