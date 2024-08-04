package com.BM.MoneyTransfer.dto;


import com.BM.MoneyTransfer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserProfileDTO {
    private String name;
    private String email;
    private String gender;

    public ViewUserProfileDTO(User user){
        this.name=user.getUserName();
        this.email=user.getEmail();
        this.gender=user.getGender();

    }
}
