package net.enigneer.JournalAPP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.enigneer.JournalAPP.entity.User;
import net.enigneer.JournalAPP.repository.UserRepo;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    //     User user=userRepo.findByUsername(username);

    //     if(user!=null){
    //         UserDetails userdetails=org.springframework.security.core.userdetails.User.builder()
    //                     .username(user.getUsername())
    //                     .password(user.getPassword())
    //                     .roles(user.getRoles().toArray(new String[0]))
    //                     .build();

    //         return userdetails;
    //     }
    //     throw new UsernameNotFoundException("User not found wiht username:"+username);

    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    System.out.println("🔍 Trying to load user: " + username);
    User user = userRepo.findByUsername(username);
    
    if(user != null){
        System.out.println("✅ User found: " + user.getUsername());
        System.out.println("Password hash: " + user.getPassword());
        System.out.println("Roles: " + user.getRoles());
        
        UserDetails userdetails = org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        return userdetails;
    }
    System.out.println("❌ User NOT found: " + username);
    throw new UsernameNotFoundException("User not found with username:" + username);
}
    
}
