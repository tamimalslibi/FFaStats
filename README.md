# FFAStats

A small custom Paper/Spigot plugin that tracks exactly: **kills, deaths, KD ratio, current kill streak, and best kill streak**, with a working PlaceholderAPI expansion so ScoreboardPL (or any PAPI-compatible plugin) can display them.

## What it does
- Tracks PvP kills and deaths automatically (via `PlayerDeathEvent`).
- Kill streak increments per kill, resets to 0 on death.
- KD is calculated live (kills / deaths, or just kills if deaths = 0).
- Stats saved per-player as YAML in `plugins/FFAStats/playerdata/<uuid>.yml`.
- Works for offline players too (stats persist between sessions).

## Placeholders (requires PlaceholderAPI installed)
| Placeholder | Description |
|---|---|
| `%ffastats_kills%` | Total kills |
| `%ffastats_deaths%` | Total deaths |
| `%ffastats_kd%` | Kill/Death ratio |
| `%ffastats_streak%` | Current kill streak |
| `%ffastats_best_streak%` | Best kill streak ever |

## In-game command
- `/ffastats` — view your own stats
- `/ffastats <player>` — view another player's stats
- Aliases: `/fstats`, `/fs`

## How to build it (you need to compile this yourself)
I can't compile Java or download Maven dependencies from this sandbox (no internet/network access here), so you'll need to build the `.jar` on your own machine or a build service. Steps:

1. Install **Java 17+** and **Maven** if you don't have them.
2. Unzip this project folder.
3. Open a terminal inside the `FFAStats` folder (the one with `pom.xml`).
4. Run:
   ```
   mvn clean package
   ```
5. Maven will download the Paper API and PlaceholderAPI as build dependencies automatically (this step needs internet access), then compile everything.
6. Your finished plugin jar will appear at `target/FFAStats.jar`.

### If you don't have Java/Maven installed
Easiest options:
- Use an online IDE like **Replit** (create a Java/Maven project, paste these files in, run `mvn package`).
- Ask whoever manages your server's hosting panel if they can compile it, or if the panel offers a build tool.
- Install Maven + a JDK locally (both are free) — this is the standard way Minecraft plugin devs build jars.

## Installation on your server
1. Put `FFAStats.jar` in your server's `/plugins` folder.
2. Make sure **PlaceholderAPI** is also installed in `/plugins` (required for the scoreboard placeholders to work).
3. Restart the server.
4. Check console for: `Hooked into PlaceholderAPI.`
5. Update your ScoreboardPL config to use the placeholders above.
