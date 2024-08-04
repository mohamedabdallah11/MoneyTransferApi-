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






}
