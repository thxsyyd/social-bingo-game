package com.socops.web;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CardDeckRestController.class)
class CardDeckRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Card deck API returns a non-empty prompt string")
    void cardDeckNextReturnsPrompt() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/api/card-deck/next"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertFalse(response.isBlank(), "Response must contain a card prompt");
    }
}
