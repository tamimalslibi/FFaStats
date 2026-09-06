package com.vertexffa.ffastats;

import org.bukkit.plugin.java.JavaPlugin;

public class FFAStats extends JavaPlugin {

    private StatsManager statsManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        statsManager = new StatsManager(this);

        getServer().getPluginManager().registerEvents(new PlayerListener(statsManager), this);

        if (getCommand("ffastats") != null) {
            getCommand("ffastats").setExecutor(new StatsCommand(statsManager));
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new FFAStatsExpansion(this, statsManager).register();
            getLogger().info("Hooked into PlaceholderAPI. Placeholders: %ffastats_kills%, %ffastats_deaths%, %ffastats_kd%, %ffastats_streak%, %ffastats_best_streak%");
        } else {
            getLogger().warning("PlaceholderAPI not found! Install it or the scoreboard placeholders will not work.");
        }

        getLogger().info("FFAStats enabled.");
    }

    @Override
    public void onDisable() {
        if (statsManager != null) {
            statsManager.saveAll();
        }
        getLogger().info("FFAStats disabled.");
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }
}
