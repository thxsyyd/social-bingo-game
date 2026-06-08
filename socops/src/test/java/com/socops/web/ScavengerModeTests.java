package com.socops.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Integration-style tests describing the expected behaviour for a Scavenger Hunt mode.
 *
 * These tests are intentionally written before implementation (TDD red). They assert
 * the external contract: lobby mode selection, a 24-item checklist, checkbox toggles
 * updating a progress meter, and a victory state when all items are checked.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScavengerModeTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Lobby offers a Scavenger Hunt mode selection")
    void modeSelectionFromLobby() {
        ResponseEntity<String> resp = restTemplate.getForEntity("/", String.class);
        String body = resp.getBody();

        assertNotNull(body, "Lobby page must be served");

        // The lobby should present an option to choose Scavenger Hunt mode
        assertTrue(body.contains("Scavenger Hunt") || body.contains("mode-select"),
                "Lobby must offer a Scavenger Hunt mode selection");
    }

    @Test
    @DisplayName("Scavenger checklist endpoint returns exactly 24 items")
    void checklistRendersTwentyFourItems() {
        ResponseEntity<List<String>> resp = restTemplate.exchange(
                "/api/scavenger/items",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );

        assertEquals(200, resp.getStatusCodeValue(), "Checklist endpoint must return 200");
        List<String> items = resp.getBody();
        assertNotNull(items, "Checklist body must not be null");
        assertEquals(24, items.size(), "Scavenger checklist must contain exactly 24 items");
    }

    @Test
    @DisplayName("Toggling checkboxes updates progress and all-checked triggers victory")
    void checkboxToggleProgressAndVictory() {
        // Load checklist
        ResponseEntity<List<String>> listResp = restTemplate.exchange(
                "/api/scavenger/items",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );

        List<String> items = listResp.getBody();
        assertNotNull(items, "Checklist must be available");
        assertEquals(24, items.size(), "Checklist must contain 24 items before interactions");

        // Toggle the first item
        ResponseEntity<Void> toggleResp = restTemplate.postForEntity(
                "/api/scavenger/toggle/0", null, Void.class);
        assertEquals(200, toggleResp.getStatusCodeValue(), "Toggling an item should return 200");

        // Progress should reflect 1/24 checked (~4.166...%)
        ResponseEntity<Double> progressResp = restTemplate.getForEntity(
                "/api/scavenger/progress", Double.class);
        assertEquals(200, progressResp.getStatusCodeValue(), "Progress endpoint should return 200");
        Double progress = progressResp.getBody();
        assertNotNull(progress, "Progress value must be present");
        assertTrue(progress > 0 && progress <= 100, "Progress must be a percentage between 0 and 100");

        // Mark all items checked (contract endpoint to check all for test convenience)
        ResponseEntity<Void> checkAll = restTemplate.postForEntity("/api/scavenger/check-all", null, Void.class);
        assertEquals(200, checkAll.getStatusCodeValue(), "Check-all helper should return 200");

        // Now the status endpoint should report victory
        ResponseEntity<Boolean> statusResp = restTemplate.getForEntity("/api/scavenger/status", Boolean.class);
        assertEquals(200, statusResp.getStatusCodeValue(), "Status endpoint should return 200");
        Boolean victory = statusResp.getBody();
        assertNotNull(victory, "Victory flag must be present");
        assertTrue(victory, "All items checked should trigger a victory state");
    }
}
