//package net.engineeringdigest.journalApp;
//
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.repository.UserRepo;
//import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//public class UserDetailsServiceImplTests {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsServiceImpl;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @BeforeEach
//    void setUp()
//    {
//        MockitoAnnotations.openMocks(this);
//    }
//    @Test
//    public void loadUserByUsernameTest()
//    {
//        User mockUser = User.builder()
//                .username("testUser")
//                .password("testPass")
//                .roles(new ArrayList<String>()) // Using Collections.emptyList() for simplicity
//                .build();
//
//        when(userRepo.findByUsername("testUser")).thenReturn(mockUser);
//        assertNotNull(userDetailsServiceImpl.loadUserByUsername("testUser"));
//    }
//}
