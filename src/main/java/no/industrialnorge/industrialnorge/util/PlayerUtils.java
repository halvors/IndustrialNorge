package no.industrialnorge.industrialnorge.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static boolean isMod(Player player) {
		return player.hasPermission("industrialnorge.group.admin");
	}
	
	public static boolean isAdmin(Player player) {
		return player.hasPermission("industrialnorge.group.admin");
	}
	
	/**
	 * Get players displayname.
	 * 
	 * @param player
	 * @return
	 */
	public static String getDisplayName(Player player) {
		String name = player.getName();
		
		if (isAdmin(player)) {
			name = ChatColor.GOLD + player.getName() + ChatColor.WHITE;
		} else if (isMod(player)) {
			name = ChatColor.BLUE + player.getName() + ChatColor.WHITE;
		}
		
		return name;
	}
	
	/**
	 * Set players displayname.
	 * 
	 * @param player
	 */
	public static void setDisplayName(Player player) {
		player.setDisplayName(getDisplayName(player));
	}
	
	/**
	 * Set players playerlist name.
	 * 
	 * @param player
	 */
	public static void setPlayerListName(Player player) {
		player.setPlayerListName(getDisplayName(player));
	}
	
	public static String getChatName(Player player) {
		String name = player.getName();
		String displayName = getDisplayName(player);
		
		if (isAdmin(player)) {
			name = ChatColor.GOLD + "[Stab] " + displayName;
		} else if (isMod(player)) {
			name = ChatColor.BLUE + "[Vakt] " + displayName;
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
