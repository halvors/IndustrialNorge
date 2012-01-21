package no.industrialnorge.industrialnorge.util;

import java.util.logging.Level;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerUtils {
	private final static IndustrialNorge plugin = IndustrialNorge.getInstance();
	
	public static boolean isAdmin(Player player) {
		return player.hasPermission("industrialnorge.group.admin");
	}
	
	public static boolean isMod(Player player) {
		return player.hasPermission("industrialnorge.group.mod");
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
		String name = getDisplayName(player);
		
		if (name.length() > 16) {
			name = name.substring(0, name.charAt(15) == '§' ? 15 : 16);
		}
		
		try {
			player.setPlayerListName(name);
		} catch (IllegalArgumentException e) {
			plugin.log(Level.INFO, "Playerlist for " + name + " was not updated. Use a shorter displayname prefix.");
		}
	}
	
	public static String getChatName(Player player) {
		String name = player.getName();
		
		if (isAdmin(player)) {
			name = ChatColor.GOLD + "[Stab] " + name;
		} else if (isMod(player)) {
			name = ChatColor.BLUE + "[Vakt] " + name;
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
