package org.halvors.halvors.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static void setDisplayName(Player player) {
		if (player.hasPermission("halvors.group.admin")) {
			player.setDisplayName(ChatColor.GOLD + "[Stab] " + player.getName() + ChatColor.WHITE);
		} else if (player.hasPermission("halvors.group.mod")) {
			player.setDisplayName(ChatColor.BLUE + "[Vakt] " + player.getName() + ChatColor.WHITE);
		}
	}
	
	/**
     * Get the best matching player.
     * 
     * @param name
     * @return
     */
    public static Player getPlayer(String name) {
        return Bukkit.getServer().matchPlayer(name).get(0);
    }
}
