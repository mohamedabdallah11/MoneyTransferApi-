package com.BM.MoneyTransfer.dto;


import com.BM.MoneyTransfer.dto.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserProfileDTO {
    private Double balance;
    private String name;
    private String email;
    private Gender gender;
    private String phoneNumber;
    private String currency;
}
