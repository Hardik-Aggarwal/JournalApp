package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;
    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }


    @PostMapping("/create-user")
    public User createEntry(@RequestBody User user)
    {
        userService.save(user);
        return user;
    }

}
