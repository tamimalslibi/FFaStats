package com.vertexffa.ffastats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final StatsManager statsManager;

    public PlayerListener(StatsManager statsManager) {
        this.statsManager = statsManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();

        PlayerStats victimStats = statsManager.getStats(victim.getUniqueId());
        victimStats.addDeath();
        statsManager.savePlayer(victim.getUniqueId());

        Player killer = victim.getKiller();
        if (killer != null && !killer.getUniqueId().equals(victim.getUniqueId())) {
            PlayerStats killerStats = statsManager.getStats(killer.getUniqueId());
            killerStats.addKill();
            statsManager.savePlayer(killer.getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        statsManager.savePlayer(event.getPlayer().getUniqueId());
    }
}
