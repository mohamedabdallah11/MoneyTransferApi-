package com.BM.MoneyTransfer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginRequestDTO {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
