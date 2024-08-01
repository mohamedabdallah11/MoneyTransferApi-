package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dto.ViewUserDTO;
import com.BM.MoneyTransfer.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(User user);

    List<User> findAll();

    void addFavorite(String recipientEmail);

    List<ViewUserDTO> findFavorite();

    void removeFavorite(String recipientEmail);
}
