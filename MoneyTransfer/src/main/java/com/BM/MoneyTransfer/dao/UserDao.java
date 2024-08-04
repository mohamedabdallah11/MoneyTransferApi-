package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    @Query("SELECT u from User u join FETCH u.cards where u.email= :email")
    Optional<User> getUserWithCards(@Param("email") String userEmail);
}
