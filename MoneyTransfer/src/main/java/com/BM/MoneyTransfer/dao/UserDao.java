package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {

}
