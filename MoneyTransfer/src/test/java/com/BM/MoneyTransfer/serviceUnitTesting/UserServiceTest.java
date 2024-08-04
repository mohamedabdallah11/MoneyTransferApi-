package com.BM.MoneyTransfer.serviceUnitTesting;

import com.BM.MoneyTransfer.dao.UserDao;
import com.BM.MoneyTransfer.service.UserServiceImpl;
import com.BM.MoneyTransfer.dto.ViewUserDTO;
import com.BM.MoneyTransfer.dto.ViewUserProfileDTO;
import com.BM.MoneyTransfer.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        User user = new User("test@example.com", "username", "password", "MALE", LocalDate.now(), "USA");

        userService.save(user);

        verify(userDao, times(1)).save(any(User.class));
        assertNotEquals("password", user.getPassword());
    }

    @Test
    void testFindAll() {
        List<User> users = List.of(new User("test@example.com", "username", "password", "MALE", LocalDate.now(), "USA"));
        when(userDao.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        verify(userDao, times(1)).findAll();
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        User user = new User("test@example.com", "username", "password", "MALE", LocalDate.now(), "USA");
        when(userDao.findById(anyString())).thenReturn(Optional.of(user));

        org.springframework.security.core.userdetails.UserDetails result = userService.loadUserByUsername("test@example.com");

        assertEquals("test@example.com", result.getUsername());
        assertEquals("password", result.getPassword());
        verify(userDao, times(1)).findById(anyString());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        when(userDao.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("test@example.com"));
    }




}
