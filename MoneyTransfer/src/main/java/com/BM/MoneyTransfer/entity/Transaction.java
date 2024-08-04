package com.BM.MoneyTransfer.entity;

import com.BM.MoneyTransfer.dto.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sender_card_number")
    @NotNull
    @Pattern(regexp = "^[0-9]{16,19}$", message = "Sender card number must be between 16 and 19 digits")
    private String senderCardNumber;

    @Column(name = "recipient_card_number")
    @NotNull
    @Pattern(regexp = "^[0-9]{16,19}$", message = "Recipient card number must be between 16 and 19 digits")
    private String recipientCardNumber;

    @Column(name = "sender_username")
    @NotNull
    @Size(min = 1, max = 255, message = "Sender username must be between 1 and 255 characters")
    private String senderUserName;

    @Column(name = "recipient_username")
    @NotNull
    @Size(min = 1, max = 255, message = "Recipient username must be between 1 and 255 characters")
    private String recipientUserName;

    @Column(name="sender_email")
    @NotNull
    @Email(message = "Sender email should be valid")
    private String senderEmail;

    @Column(name="recipient_email")
    @NotNull
    @Email(message = "Recipient email should be valid")
    private String recipientEmail;

    @Column(name = "amount")
    @NotNull
    @Positive(message = "Amount must be positive")
    private Double amount;

    @Column(name = "date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent(message = "Date must be in the past or present")
    private Date date;

    @Column(name = "status")
    @NotNull
    private Status status;

    public Transaction(String senderCardNumber, String recipientCardNumber, String senderUserName, String recipientUserName, String senderEmail, String recipientEmail, Double amount, Date date, Status status) {
        this.senderCardNumber = senderCardNumber;
        this.recipientCardNumber = recipientCardNumber;
        this.senderUserName = senderUserName;
        this.recipientUserName = recipientUserName;
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }
}
