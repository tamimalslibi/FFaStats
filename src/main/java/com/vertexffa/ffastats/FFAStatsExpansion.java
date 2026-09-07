package com.vertexffa.ffastats;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class FFAStatsExpansion extends PlaceholderExpansion {

    private final FFAStats plugin;
    private final StatsManager statsManager;

    public FFAStatsExpansion(FFAStats plugin, StatsManager statsManager) {
        this.plugin = plugin;
        this.statsManager = statsManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ffastats";
    }

    @Override
    public @NotNull String getAuthor() {
        return "VertexFFA";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null) {
            return "";
        }

        PlayerStats stats = statsManager.getStats(player.getUniqueId());

        switch (params.toLowerCase()) {
            case "kills":
                return String.valueOf(stats.getKills());
            case "deaths":
                return String.valueOf(stats.getDeaths());
            case "kd":
                return String.valueOf(stats.getKD());
            case "streak":
            case "current_streak":
                return String.valueOf(stats.getCurrentStreak());
            case "best_streak":
                return String.valueOf(stats.getBestStreak());
            default:
                return null;
        }
    }
}
