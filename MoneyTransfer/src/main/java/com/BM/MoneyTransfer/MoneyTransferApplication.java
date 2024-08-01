package com.BM.MoneyTransfer;

import com.BM.MoneyTransfer.dao.UserDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MoneyTransferApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(UserDao userDao, PasswordEncoder passwordEncoder) {
        return runner -> {
//            User user = userDao.findById("abdulrahman@gmail.com").orElse(null);
//            if (user != null) {
//                user.setPassword(passwordEncoder.encode("123"));
//                userDao.save(user);
//            }


        };
    }
}
