package com.BM.MoneyTransfer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    @Column(name = "country")
    private String country;

    @Column(name = "is_active")
    private Integer isActive = 1;


    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "favourite_recipients",
            joinColumns = @JoinColumn(name = "user_email1"),
            inverseJoinColumns = @JoinColumn(name = "user_email2")
    )
    List<User> favouriteRecipients = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Card> cards = new ArrayList<>();

    public User(String email, String userName, String password, String gender, Date dateOfBirth, String country) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
    }

    void addFavouriteRecipient(User user) {
        favouriteRecipients.add(user);
    }

    void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    void setCards(List<Card> cards) {
        for (Card card : cards) {
            card.setUser(this);
        }
        this.cards = cards;
    }

}
