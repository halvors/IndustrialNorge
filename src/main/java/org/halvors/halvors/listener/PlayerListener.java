package org.halvors.halvors.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.PlayerUtils;
import org.halvors.halvors.util.RepairUtils;

public class PlayerListener extends org.bukkit.event.player.PlayerListener {
//	private final halvors plugin;
	
	public PlayerListener(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname.
		PlayerUtils.setDisplayName(player);
		
		event.setJoinMessage(player.getDisplayName() + ChatColor.GREEN + " logget på.");
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(player.getDisplayName() + ChatColor.RED + " logget av.");
	}
	
	@Override
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
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
	
	@Override
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			
			// Infinite tools and armor.
			RepairUtils.repair(player.getItemInHand(), player);
		}
	}
}
