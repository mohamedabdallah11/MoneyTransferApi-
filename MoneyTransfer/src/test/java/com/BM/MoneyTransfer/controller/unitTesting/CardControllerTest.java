package com.BM.MoneyTransfer.controller.unitTesting;

import com.BM.MoneyTransfer.controller.CardController;
import com.BM.MoneyTransfer.dto.CheckBalanceDTO;
import com.BM.MoneyTransfer.dto.UpdateCardBalanceDTO;
import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("123456");
        when(cardService.addCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(post("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("123456"));

        verify(cardService, times(1)).addCard(any(Card.class));
    }

    @Test
    public void testGetCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("123456");
        when(cardService.getCard("123456")).thenReturn(card);

        mockMvc.perform(get("/api/cards/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("123456"));

        verify(cardService, times(1)).getCard("123456");
    }

    @Test
    public void testGetAllCards() throws Exception {
        Card card = new Card();
        card.setCardNumber("123456");
        when(cardService.getAllCards()).thenReturn(Collections.singletonList(card));

        mockMvc.perform(get("/api/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardNumber").value("123456"));

        verify(cardService, times(1)).getAllCards();
    }

    @Test
    public void testUpdateCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("123456");
        when(cardService.updateCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(put("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("123456"));

        verify(cardService, times(1)).updateCard(any(Card.class));
    }

    @Test
    public void testDeleteCard() throws Exception {
        doNothing().when(cardService).deleteCard("123456");

        mockMvc.perform(delete("/api/cards/123456"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).deleteCard("123456");
    }

    @Test
    public void testGetCardBalance() throws Exception {
        CheckBalanceDTO dto = new CheckBalanceDTO("123456");
        when(cardService.getCardBalance("123456")).thenReturn(100.0);

        mockMvc.perform(get("/api/cards/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(100.0));

        verify(cardService, times(1)).getCardBalance("123456");
    }

    @Test
    public void testUpdateCardBalance() throws Exception {
        UpdateCardBalanceDTO dto = new UpdateCardBalanceDTO("123456", 200.0);
        doNothing().when(cardService).updateCardBalance("123456", 200.0);

        mockMvc.perform(put("/api/cards/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(cardService, times(1)).updateCardBalance("123456", 200.0);
    }
}
