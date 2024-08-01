package com.BM.MoneyTransfer.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginDTO {

    private String email;
    private String password;
}
