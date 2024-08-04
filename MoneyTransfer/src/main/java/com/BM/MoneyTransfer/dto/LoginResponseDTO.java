package com.BM.MoneyTransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {
    private HttpStatus status;
    private String tokenType;
    private String message;
    private String token;
}
