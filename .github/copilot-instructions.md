# Copilot Instructions

- [ ] Run `./mvnw clean package` in `socops`
- [ ] Run `./mvnw test` in `socops`
- [ ] Fix lint issues before submitting changes

Use the repo README and `workshop/GUIDE.md` for context.

Agent rules:
- Prefer small, local changes and avoid broad refactors unless asked.
- Validate backend changes with `./mvnw test`.
- For UI work, check `socops/src/main/resources/templates/game.html` and `socops/src/main/resources/static/css/app.css`.
- Link to existing docs rather than copying them.

Design guide:
- Keep UI changes minimal and intentional; avoid introducing large new frameworks or unnecessary animation.
- Favor clean typography, restrained spacing, and coherent visual hierarchy.
- For frontend edits, prefer `game.html` and `app.css` as the main touchpoints.
- Preserve existing game behavior unless the user explicitly requests functional changes.
- When adding visuals, choose subtle accents and structural elements over heavy imagery.
