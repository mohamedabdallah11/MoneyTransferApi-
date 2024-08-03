package com.BM.MoneyTransfer.controller.unitTesting;
import com.BM.MoneyTransfer.controller.CardController;

import com.BM.MoneyTransfer.entity.Card;
import com.BM.MoneyTransfer.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cardController).build();
    }

    @Test
    public void testAddCard() throws Exception {
        Card card = new Card();
        when(cardService.addCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(post("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234\",\"balance\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1234"));

        verify(cardService, times(1)).addCard(any(Card.class));
    }

    @Test
    public void testGetCard() throws Exception {
        Card card = new Card();
        card.setCardNumber("1234");
        when(cardService.getCard("1234")).thenReturn(card);

        mockMvc.perform(get("/api/cards/1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1234"));

        verify(cardService, times(1)).getCard("1234");
    }

    @Test
    public void testGetAllCards() throws Exception {
        mockMvc.perform(get("/api/cards"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).getAllCards();
    }

    @Test
    public void testUpdateCard() throws Exception {
        Card card = new Card();
        when(cardService.updateCard(any(Card.class))).thenReturn(card);

        mockMvc.perform(put("/api/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234\",\"balance\":200.0}"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).updateCard(any(Card.class));
    }

    @Test
    public void testDeleteCard() throws Exception {
        doNothing().when(cardService).deleteCard("1234");

        mockMvc.perform(delete("/api/cards/1234"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).deleteCard("1234");
    }

    @Test
    public void testGetCardBalance() throws Exception {
        when(cardService.getCardBalance("1234")).thenReturn(100.0);

        mockMvc.perform(get("/api/cards/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));

        verify(cardService, times(1)).getCardBalance("1234");
    }

    @Test
    public void testUpdateCardBalance() throws Exception {
        doNothing().when(cardService).updateCardBalance("1234", 200.0);

        mockMvc.perform(put("/api/cards/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cardNumber\":\"1234\",\"newBalance\":200.0}"))
                .andExpect(status().isOk());

        verify(cardService, times(1)).updateCardBalance("1234", 200.0);
    }
}
