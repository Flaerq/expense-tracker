package ua.vitaliy.expensetracker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.vitaliy.expensetracker.model.User;
import ua.vitaliy.expensetracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the UserService, covering various scenarios.
 * Ensures that CRUD operations and user-related functionalities work as expected.
 */
@ExtendWith({MockitoExtension.class})
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {

    private static User user;


    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void init(){
        user = new User("Vitaliy", "test@gmail.com", "qwerty");
    }

    @Test
    void shouldReturnUserById(){
        int id = 2;
        User mockUser = new User();
        mockUser.setUserId(id);

        when(userService.readById(anyInt())).thenReturn(Optional.of(mockUser));

        User resultUser = userService.readById(2).get();

        assertEquals(id, resultUser.getUserId());
    }


    @Test
    void shouldReturnCorrectUserCount(){
        long count = 20;

        when(userService.usersCount()).thenReturn(count);

        long result = userService.usersCount();

        assertEquals(count, result);
    }

    @Test
    void shouldReturnAllUsers(){
        User user1 = new User("Vitaliy","test@gmail.com","hello");
        User user2 = new User("Vlad","gmail@gmail.com", "qwerty");

        List<User> users = new ArrayList<>(Arrays.asList(user1, user2));

        when(userService.readAll()).thenReturn(users);

        List<User> result = userService.readAll();

        assertIterableEquals(users,result);
    }

    @Test
    void shouldDeleteUserById(){
        int id = 0;

        when(userRepository.existsById(0)).thenReturn(true);

        userService.deleteById(id);

        verify(userRepository).deleteById(id);
    }

    @Test
    void shouldDeleteUserByUserObject(){
        user.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(true);

        userService.delete(user);

        verify(userRepository).delete(user);
    }

    @Test
    void shouldSaveUser(){
        when(userService.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertEquals(user, result);
    }

    @Test
    void shouldUpdateUserByIdAndUserObject(){
        int id = 2;
        user.setUserId(2);

        User updatedUser = new User("Vladislav","test@gmail.com", "password");
        updatedUser.setUserId(id);

        when(userRepository.findById(2)).thenReturn(Optional.of(user));


        when(userService.update(2, user)).thenReturn(updatedUser);

        User result = userService.update(id, updatedUser);

        assertEquals(updatedUser, result);
    }
}
