package com.BM.MoneyTransfer.controller;

import com.BM.MoneyTransfer.dto.CheckBalanceDTO;
import com.BM.MoneyTransfer.dto.UpdateCardBalanceDTO;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<?> addCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<?> getCard(@PathVariable String cardNumber) {
        Card card = cardService.getCard(cardNumber);
        if (card != null) {
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.updateCard(card));
    }

    @DeleteMapping("/{cardNumber}")
    public ResponseEntity<?> deleteCard(@PathVariable String cardNumber) {
        cardService.deleteCard(cardNumber);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getCardBalance(@RequestBody CheckBalanceDTO dto) {
        Double balance = cardService.getCardBalance(dto.getCardNumber());
        if (balance != null) {
            return ResponseEntity.ok(balance);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/balance")
    public ResponseEntity<?> updateCardBalance(@RequestBody UpdateCardBalanceDTO dto) {
        cardService.updateCardBalance(dto.getCardNumber(), dto.getNewBalance());
        return ResponseEntity.ok().build();
    }
}
