package com.BM.MoneyTransfer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "card_number")
    @NotNull
    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must be 16 digits")
    private String cardNumber;

    @Column(name = "cardholder_name")
    @NotNull
    @Size(min = 1, max = 100, message = "Cardholder name must be between 1 and 100 characters")
    private String cardholderName;

    @Column(name = "expiration_date")
    @NotNull
    @Pattern(regexp = "^(\\d{4})-(0[1-9]|1[0-2])$", message = "Expiration date must be in YYYY-MM format")
    private String expirationDate;

    @Column(name = "cvv")
    @NotNull
    @Pattern(regexp = "^[0-9]{3,4}$", message = "CVV must be 3 or 4 digits")
    private String cvv;

    @Column(name = "pin")
    @NotNull
    @Pattern(regexp = "^[0-9]{4,6}$", message = "PIN must be 4 to 6 digits")
    private String pin;

    @Column(name = "balance")
    @NotNull
    @PositiveOrZero(message = "Balance must be zero or positive")
    private Double balance;

    @Column(name = "currency")
    @NotNull
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter ISO code")
    private String currency;

    @Column(name = "account_type")
    @NotNull
    @Size(min = 1, max = 50, message = "Account type must be between 1 and 50 characters")
    private String accountType;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_email")
    private User user;

    public Card(String cardNumber, String cardholderName, String expirationDate, String cvv, String pin, Double balance, String currency, String accountType) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.pin = pin;
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
    }

    public Card(String cardNumber, Double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }
}
