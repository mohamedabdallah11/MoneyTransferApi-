package com.BM.MoneyTransfer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferRequestDTO {
    @NotNull
    private Double sendAmount;

    @NotNull
    private String sendCurrency;

    @NotNull
    private String receiverName;

    @NotNull
    private String receiverEmail; // Ask the front-end what is meant by account in the Figma design (email or card number)

    @NotNull
    private Double receiveAmount;

    @NotNull
    private String receiveCurrency;
}
