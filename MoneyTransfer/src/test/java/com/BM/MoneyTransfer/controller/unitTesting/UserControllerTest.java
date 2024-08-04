package com.BM.MoneyTransfer.controller.unitTesting;
import com.BM.MoneyTransfer.controller.UserController;
import com.BM.MoneyTransfer.dto.LoginResponseDTO;
import com.BM.MoneyTransfer.dto.SignUpRequestDTO;
import com.BM.MoneyTransfer.dto.UserLoginRequestDTO;
import com.BM.MoneyTransfer.dto.ViewUserProfileDTO;
import com.BM.MoneyTransfer.service.JwtService;
import com.BM.MoneyTransfer.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        when(bindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> responseEntity = userController.register(signUpRequestDTO, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("User registered successfully", responseEntity.getBody());
        verify(userService, times(1)).save(any());
    }

    @Test
    void testRegister_ValidationErrors() {
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(new FieldError("field", "field", "error")));

        ResponseEntity<?> responseEntity = userController.register(signUpRequestDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(Map.of("field", "error"), responseEntity.getBody());
        verify(userService, times(0)).save(any());
    }

    @Test
    void testLogin_Success() {
        UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO();
        userLoginRequestDTO.setEmail("test@example.com");
        userLoginRequestDTO.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(mock(Authentication.class));
        when(jwtService.generateToken(anyString())).thenReturn("jwtToken");

        ResponseEntity<?> responseEntity = userController.login(userLoginRequestDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(new LoginResponseDTO(HttpStatus.OK, "Bearer", "Login successful!", "jwtToken"), responseEntity.getBody());
    }

    @Test
    void testLogin_Failure() {
        UserLoginRequestDTO userLoginRequestDTO = new UserLoginRequestDTO();
        userLoginRequestDTO.setEmail("test@example.com");
        userLoginRequestDTO.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Invalid credentials"));

        ResponseEntity<?> responseEntity = userController.login(userLoginRequestDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }


}
