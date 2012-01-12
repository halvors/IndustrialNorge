package no.industrialnorge.industrialnorge.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static void setDisplayName(Player player) {
		if (player.hasPermission("industrialnorge.group.admin")) {
			player.setDisplayName(ChatColor.GOLD + player.getName() + ChatColor.WHITE);
		} else if (player.hasPermission("industrialnorge.group.mod")) {
			player.setDisplayName(ChatColor.BLUE + player.getName() + ChatColor.WHITE);
		}
	}
	
	public static String getChatName(Player player) {
		String name = player.getName();
		
		if (player.hasPermission("industrialnorge.group.admin")) {
			name = ChatColor.GOLD + "[Stab] " + player.getName();
		} else if (player.hasPermission("industrialnorge.group.mod")) {
			name = ChatColor.BLUE + "[Vakt] " + player.getName();
		}
		
		return name;
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
