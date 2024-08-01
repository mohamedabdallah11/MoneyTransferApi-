package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(User user);
}
