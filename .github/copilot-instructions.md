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
