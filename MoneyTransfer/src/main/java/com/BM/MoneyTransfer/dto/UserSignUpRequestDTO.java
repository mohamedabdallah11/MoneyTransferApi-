package com.BM.MoneyTransfer.dto;

import com.BM.MoneyTransfer.dto.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class UserSignUpRequestDTO {
    @NotNull
    private String userName;
    @NotNull
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull
    private String country;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;

    private String phoneNumber;
}
