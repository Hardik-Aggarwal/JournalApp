package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    JournalEntryRepo journalEntryRepo;
    @Autowired
    UserService userService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName)
    {

        try {
            User user = userService.getByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved= journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving entry");
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll()
    {
        return (journalEntryRepo.findAll());
    }
    public Optional<JournalEntry> getById(ObjectId myId)
    {
        return journalEntryRepo.findById(myId);
    }
    @Transactional
    public boolean deleteById(String userName,ObjectId myId)
    {
        boolean removed = false;
        try {
            User user = userService.getByUsername(userName);
            removed = user.getJournalEntries().removeIf(x->x.getId().equals(myId));
            if(removed==true)
            {
                userService.saveEntry(user);
                journalEntryRepo.deleteById(myId);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Error in deleting the Journal Id "+e);
        }
        return removed;
    }
}
