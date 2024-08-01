package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
