package com.BM.MoneyTransfer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class UserLoginRequestDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
