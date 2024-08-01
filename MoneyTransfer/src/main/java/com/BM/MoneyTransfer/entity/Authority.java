package com.BM.MoneyTransfer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority {
    @Id
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "authority")
    private String role;

    public Authority(String userEmail, String role) {
        this.userEmail = userEmail;
        this.role = role;
    }
}
