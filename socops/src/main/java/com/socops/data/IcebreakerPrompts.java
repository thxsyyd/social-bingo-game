package com.socops.data;

import java.util.List;

/**
 * Central catalogue of every icebreaker prompt that can appear on a board.
 * Exactly 24 entries — one fewer than the 25-cell grid, because the
 * centre cell is always the free space.
 */
public final class IcebreakerPrompts {

    public static final String FREE_CELL_LABEL = "FREE SPACE";

    public static final List<String> ALL_PROMPTS = List.of(
            "used Vim on a smartphone",
            "deployed code at 3 a.m.",
            "fixes bugs with duct tape metaphors",
            "accidentally committed secrets",
            "uses a terminal as a music sequencer",
            "renamed a branch after a meme",
            "prefers ASCII art README files",
            "keeps 100+ browser tabs open",
            "wrote code while riding a bike",
            "has a favorite error message",
            "uses emojis in commit messages",
            "owns a sticker-covered laptop",
            "debugged production with a rubber duck",
            "built something just for fun",
            "comments code with song lyrics",
            "owns more than three hoodies",
            "talks about cloud vendors for fun",
            "uses a formatter as a lifestyle choice",
            "keeps a secret terminal alias",
            "orders snacks during deploys",
            "thinks in regex too often",
            "has a favorite pair programming snack",
            "uses a conference badge as a bookmark",
            "gave a server a pet name"
    );

    private IcebreakerPrompts() {
        /* catalogue only — no instances */
    }
}
