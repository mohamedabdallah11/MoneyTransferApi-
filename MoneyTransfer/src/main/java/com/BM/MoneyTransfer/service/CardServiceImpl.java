package com.BM.MoneyTransfer.service;

import com.BM.MoneyTransfer.dao.CardDao;
import com.BM.MoneyTransfer.dao.UserDao;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final UserDao userDao;

    @Autowired
    public CardServiceImpl(CardDao cardDao, UserDao userDao) {
        this.cardDao = cardDao;
        this.userDao = userDao;
    }

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    @Transactional
    public Card addCard(Card card) {
        String email = getCurrentUserEmail();
        card.setUser(userDao.findById(email).orElse(null));
        return cardDao.save(card);
    }

    @Override
    public Card getCard(String cardNumber) {
        return cardDao.findById(cardNumber).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCards() {
        User user = userDao.findById(getCurrentUserEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getCards();
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
