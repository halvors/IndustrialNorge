package no.industrialnorge.industrialnorge.listener;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends org.bukkit.event.player.PlayerListener {
//	private final halvors plugin;
	
	public PlayerListener(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname and listname.
		PlayerUtils.setDisplayName(player);
		PlayerUtils.setPlayerListName(player);
		
		event.setJoinMessage(player.getDisplayName() + ChatColor.GREEN + " logget på.");
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname and listname.
		PlayerUtils.setDisplayName(player);
		PlayerUtils.setPlayerListName(player);
		
		event.setQuitMessage(player.getDisplayName() + ChatColor.RED + " logget av.");
	}
	
	@Override
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
		event.setFormat(PlayerUtils.getChatName(player) + ": " + ChatColor.WHITE + event.getMessage());
	}
}
