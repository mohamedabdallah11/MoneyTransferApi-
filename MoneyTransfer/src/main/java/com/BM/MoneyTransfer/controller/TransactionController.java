//package com.BM.MoneyTransfer.controller;
//
//import com.BM.MoneyTransfer.dto.TransactionDto;
//import com.BM.MoneyTransfer.entity.Transaction;
//import com.BM.MoneyTransfer.service.TransactionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class TransactionController {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    @GetMapping("/transactions")
//    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam String cardNumber) {
//        return ResponseEntity.ok().build();
//    }
//
//    // Transfer money
//    @PostMapping("/transfer")
//    public ResponseEntity<Transaction> transferMoney(@RequestBody TransactionDto transactionDto) {
//        return ResponseEntity.ok().build();
//    }
//}
