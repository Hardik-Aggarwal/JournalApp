package net.engineeringdigest.journalApp.service;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    public void save(User user)
    {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        } catch (Exception e) {
            log.error("Duplicate user {}",user.getUsername(),e);
        }

    }
    public void saveEntry(User user)
    {
        userRepo.save(user);
    }

    public List<User> getAll()
    {
        return (userRepo.findAll());
    }
    public Optional<User> getById(ObjectId myId)
    {
        return userRepo.findById(myId);
    }
    public void deleteById(ObjectId myId)
    {
        userRepo.deleteById(myId);
    }
    public User getByUsername(String username)
    {
        return userRepo.findByUsername(username);
    }
    public void deleteByUsername(String username)
    {
        userRepo.deleteByUsername(username);
    }
    public void saveAdmin(User user)
    {
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }
}
