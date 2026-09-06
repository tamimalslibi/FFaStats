package com.vertexffa.ffastats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {

    private final StatsManager statsManager;

    public StatsCommand(StatsManager statsManager) {
        this.statsManager = statsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OfflinePlayer target;

        if (args.length > 0) {
            target = Bukkit.getOfflinePlayer(args[0]);
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            sender.sendMessage(ChatColor.RED + "Please specify a player: /ffastats <player>");
            return true;
        }

        PlayerStats stats = statsManager.getStats(target.getUniqueId());

        sender.sendMessage(ChatColor.GOLD + "--- Stats for " + target.getName() + " ---");
        sender.sendMessage(ChatColor.GRAY + "Kills: " + ChatColor.GREEN + stats.getKills());
        sender.sendMessage(ChatColor.GRAY + "Deaths: " + ChatColor.RED + stats.getDeaths());
        sender.sendMessage(ChatColor.GRAY + "KD: " + ChatColor.YELLOW + stats.getKD());
        sender.sendMessage(ChatColor.GRAY + "Current Streak: " + ChatColor.AQUA + stats.getCurrentStreak());
        sender.sendMessage(ChatColor.GRAY + "Best Streak: " + ChatColor.LIGHT_PURPLE + stats.getBestStreak());
        return true;
    }
}
