package com.BM.MoneyTransfer.dto;

import com.BM.MoneyTransfer.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    @Email(message = "Email is not valid")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Size(min = 6, max = 255, message = "Username must be between 6 and 255 characters")
    @NotNull(message = "Username cannot be empty")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 60, message = "Password must be at least 6 characters and at most 60 characters long")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", message = "Password must contain at least one letter, one number, and one special character")
    private String password;

//    @Enumerated(EnumType.STRING)
    private String gender;

//    @NotNull(message = "Date of birth cannot be null")
//    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Country cannot be null")
    @Size(min = 1, max = 50, message = "Country must be between 1 and 50 characters")
    private String country;

//    @NotNull(message = "Active status cannot be null")
    @Min(value = 0, message = "Active status must be 0 or 1")
    @Max(value = 1, message = "Active status must be 0 or 1")
    private Integer isActive = 1;

    public User toUser() {
        return new User(this.getEmail(),
                this.getUserName(),
                this.getPassword(),
                this.getGender(),
                this.getDateOfBirth(),
                this.getCountry());
    }

}
