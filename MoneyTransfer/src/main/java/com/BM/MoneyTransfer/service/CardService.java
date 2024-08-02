package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.entity.Card;

import java.util.List;

public interface CardService {
    Card addCard(Card card);
    Card getCard(String cardNumber);
    List<Card> getAllCards();
    Card updateCard(Card card);
    void deleteCard(String cardNumber);

    Double getCardBalance(String cardNumber); // Method to get card balance
    void updateCardBalance(String cardNumber, Double newBalance); // Method to update card balance
}
