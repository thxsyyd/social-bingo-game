package com.socops.web;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.socops.data.IcebreakerPrompts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoints for the Scavenger Hunt mode.
 */
@RestController
@RequestMapping("/api/scavenger")
public class ScavengerRestController {

    private static final List<String> ITEMS = IcebreakerPrompts.ALL_PROMPTS;
    private final Set<Integer> checkedItemIndices = ConcurrentHashMap.newKeySet();

    @GetMapping("/items")
    public List<String> getItems() {
        return ITEMS;
    }

    @PostMapping("/toggle/{itemId}")
    public void toggleItem(@PathVariable int itemId) {
        if (!isValidItemIndex(itemId)) {
            return;
        }

        if (!checkedItemIndices.remove(itemId)) {
            checkedItemIndices.add(itemId);
        }
    }

    @GetMapping("/progress")
    public double getProgress() {
        return itemCount() == 0 ? 0.0 : 100.0 * checkedItemIndices.size() / itemCount();
    }

    @PostMapping("/check-all")
    public void checkAll() {
        for (int itemId = 0; itemId < itemCount(); itemId++) {
            checkedItemIndices.add(itemId);
        }
    }

    @GetMapping("/status")
    public boolean getVictoryStatus() {
        return checkedItemIndices.size() == itemCount();
    }

    private boolean isValidItemIndex(int itemId) {
        return itemId >= 0 && itemId < itemCount();
    }

    private int itemCount() {
        return ITEMS.size();
    }
}
