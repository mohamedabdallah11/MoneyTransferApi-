package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dao.CardDao;
import com.BM.MoneyTransfer.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;

    @Autowired
    public CardServiceImpl(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    @Override
    public Card addCard(Card card) {
        return cardDao.save(card);
    }

    @Override
    public Card getCard(String cardNumber) {
        return cardDao.findById(cardNumber).orElse(null);
    }

    @Override
    public List<Card> getAllCards() {
        return cardDao.findAll();
    }

    @Override
    public Card updateCard(Card card) {
        return cardDao.save(card);
    }

    @Override
    public void deleteCard(String cardNumber) {
        cardDao.deleteById(cardNumber);
    }

    @Override
    public Double getCardBalance(String cardNumber) {
        Card card = cardDao.findById(cardNumber).orElse(null);
        if (card != null) {
            return card.getBalance();
        }
        return null;
    }

    @Override
    public void updateCardBalance(String cardNumber, Double newBalance) {
        Card card = cardDao.findById(cardNumber).orElse(null);
        if (card != null) {
            card.setBalance(newBalance);
            cardDao.save(card);
        }
    }
}
