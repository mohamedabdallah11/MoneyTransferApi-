package com.BM.MoneyTransfer.dao;

import com.BM.MoneyTransfer.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority, String> {
}
