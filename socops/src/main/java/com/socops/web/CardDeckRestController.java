package com.socops.web;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socops.data.IcebreakerPrompts;

/** REST endpoints for the Card Deck Shuffle mode. */
@RestController
@RequestMapping("/api/card-deck")
public class CardDeckRestController {

    @GetMapping("/next")
    public String drawNextCard() {
        List<String> prompts = IcebreakerPrompts.ALL_PROMPTS;
        int index = ThreadLocalRandom.current().nextInt(prompts.size());
        return prompts.get(index);
    }
}
