package com.BM.MoneyTransfer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserDTO {
    private String name;
    private String email;
    private String phoneNumber;
}
