package org.halvors.halvors.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.PlayerUtils;
import org.halvors.halvors.util.RepairUtils;

public class PlayerListener extends org.bukkit.event.player.PlayerListener {
//	private final halvors plugin;
//	private ConfigurationManager configManager;
	
	public PlayerListener(halvors plugin) {
//		this.plugin = plugin;
//		this.configManager = plugin.getConfigurationManager();
	}
	
	@Override
	public void onPlayerPreLogin(PlayerPreLoginEvent event) {
//		if (configManager.isMaintenance && !PlayerUtils.isAdmin(event.getName())) {
//			event.disallow(Result.KICK_OTHER, "Serveren er nede på grunn av vedlikehold.");
//		}
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname.
		PlayerUtils.setDisplayName(player);
		
		event.setJoinMessage(player.getDisplayName() + ChatColor.GREEN + " logget p�.");
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(player.getDisplayName() + ChatColor.RED + " logget av.");
	}
	
	@Override
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname.
		PlayerUtils.setDisplayName(player);
		
		event.setFormat(player.getDisplayName() + ": " + event.getMessage());
	}
	
	@Override
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.isCancelled()) {
			Action action = event.getAction();
			Player player = event.getPlayer();
			
			
			if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
				// Infinite tools and armor.
				RepairUtils.repair(player.getItemInHand(), player);
			}
		}
	}
}
