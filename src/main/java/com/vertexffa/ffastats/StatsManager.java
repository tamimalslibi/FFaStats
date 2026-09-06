package com.vertexffa.ffastats;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StatsManager {

    private final FFAStats plugin;
    private final Map<UUID, PlayerStats> cache = new HashMap<>();
    private final File dataFolder;

    public StatsManager(FFAStats plugin) {
        this.plugin = plugin;
        this.dataFolder = new File(plugin.getDataFolder(), "playerdata");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public PlayerStats getStats(UUID uuid) {
        return cache.computeIfAbsent(uuid, this::loadPlayer);
    }

    public PlayerStats getStats(OfflinePlayer player) {
        return getStats(player.getUniqueId());
    }

    private PlayerStats loadPlayer(UUID uuid) {
        PlayerStats stats = new PlayerStats();
        File file = new File(dataFolder, uuid.toString() + ".yml");
        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            stats.setKills(config.getInt("kills", 0));
            stats.setDeaths(config.getInt("deaths", 0));
            stats.setCurrentStreak(config.getInt("current-streak", 0));
            stats.setBestStreak(config.getInt("best-streak", 0));
        }
        return stats;
    }

    public void savePlayer(UUID uuid) {
        PlayerStats stats = cache.get(uuid);
        if (stats == null) {
            return;
        }
        File file = new File(dataFolder, uuid.toString() + ".yml");
        FileConfiguration config = new YamlConfiguration();
        config.set("kills", stats.getKills());
        config.set("deaths", stats.getDeaths());
        config.set("current-streak", stats.getCurrentStreak());
        config.set("best-streak", stats.getBestStreak());
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Could not save stats for " + uuid + ": " + e.getMessage());
        }
    }

    public void saveAll() {
        for (UUID uuid : cache.keySet()) {
            savePlayer(uuid);
        }
    }
}
