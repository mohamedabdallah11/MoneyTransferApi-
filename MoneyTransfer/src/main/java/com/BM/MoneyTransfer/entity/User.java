package com.BM.MoneyTransfer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "email")
    @Email(message = "Email is not valid")
    private String email;

    @Size(min = 6)
    @Column(name = "username")
    private String userName;


    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;


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
    @JsonIgnore
    List<User> favouriteRecipients = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    List<Card> cards = new ArrayList<>();


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_email")
    @JsonIgnore
    List<Authority> authorities = new ArrayList<>();

    public User(String email, String userName, String password, String gender, LocalDate dateOfBirth, String country) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
    }

    public void addFavouriteRecipient(User user) {
        favouriteRecipients.add(user);
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    public void setCards(List<Card> cards) {
        for (Card card : cards) {
            card.setUser(this);
        }
        this.cards = cards;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

}
