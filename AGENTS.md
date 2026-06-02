# AGENTS.md — Guidance for AI coding agents

Purpose: give concise, actionable pointers so an AI agent can be productive immediately.

- **Prereqs**: Java 21 (JDK) and Maven (or use `./mvnw`).
- **Run (dev)**: `cd socops && ./mvnw spring-boot:run` (app listens on port 8080).
- **Build**: `cd socops && ./mvnw clean package`
- **Test**: `cd socops && ./mvnw test`

Key locations:

- `socops/pom.xml` — project build config
- `socops/src/main/java` — backend sources (main package `com.socops`)
- `socops/src/main/resources/templates/game.html` — main UI template
- `.github/agents/` — example agent workflows and automation
- `.github/instructions/` — workspace-specific instructions (frontend, CSS utilities)
- `workshop/GUIDE.md` — lab/workshop guide with usage context
- `README.md` — high-level project overview

Agent behavior guidance (concise):

- Prefer minimal, local changes; open a PR for larger refactors.
- Run `./mvnw test` before suggesting code changes that affect logic.
- Link to existing docs instead of duplicating content.
- When modifying frontend templates, check `game.html` and `static/css/app.css`.

If you need more specialized instructions (e.g. frontend-only agents, test-runners), ask to create a dedicated `.github/copilot-instructions.md` or extra AGENT files.
