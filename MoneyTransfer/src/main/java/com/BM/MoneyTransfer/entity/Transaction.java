package com.BM.MoneyTransfer.entity;


import jakarta.persistence.*;
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
    private String senderCardNumber;

    @Column(name = "recipient_card_number")
    private String recipientCardNumber;

    @Column(name = "sender_username")
    private String senderUserName;

    @Column(name = "recipient_username")
    private String recipientUserName;

    @Column(name="send_email")
    private String senderEmail;

    @Column(name="recipient_email")
    private String recipientEmail;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;
//
//    public Transaction(String senderCardNumber, String recipientCardNumber, String senderUserName, String recipientUserName, Double amount, Date date, String status) {
//        this.senderCardNumber = senderCardNumber;
//        this.recipientCardNumber = recipientCardNumber;
//        this.senderUserName = senderUserName;
//        this.recipientUserName = recipientUserName;
//        this.amount = amount;
//        this.date = date;
//        this.status = status;
//    }

}
