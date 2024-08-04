package com.BM.MoneyTransfer.controller.unitTesting;
import com.BM.MoneyTransfer.controller.TransactionController;
import com.BM.MoneyTransfer.dto.TransferResponseDTO;
import com.BM.MoneyTransfer.entity.Transaction;
import com.BM.MoneyTransfer.dto.enums.Status;
import com.BM.MoneyTransfer.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransactionControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(transactionController)
                .build();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction(
                "1234567890123456", "6543210987654321",
                "senderUser", "recipientUser",
                "sender@example.com", "recipient@example.com",
                100.0, new Date(), Status.APPROVED);

        TransferResponseDTO response = TransferResponseDTO.builder()
                .transactionId(transaction.getId().toString())
                .status(transaction.getStatus().name())
                .message("Transaction approved")
                .build();

        when(transactionService.save(any(Transaction.class))).thenReturn(transaction);

        mockMvc.perform(post("/api/transfer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(transaction))
                        .principal(() -> "sender@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", is(transaction.getId().toString())))
                .andExpect(jsonPath("$.status", is(transaction.getStatus().name())))
                .andExpect(jsonPath("$.message", is("Transaction approved")));
    }

    @Test
    public void testGetTransactionHistory() throws Exception {
        Page<Transaction> transactionPage = mock(Page.class);
        when(transactionService.findByEmail(anyString(), any(Pageable.class))).thenReturn(transactionPage);

        mockMvc.perform(get("/api/transactions")
                        .principal(() -> "sender@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
