package no.industrialnorge.industrialnorge.listener;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;
import no.industrialnorge.industrialnorge.util.Utils;

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
		
//		// Display the Motd.
//		if (player.hasPermission("industrialnorge.motd")) {
//			player.sendMessage(Utils.getMotd());
//		}
		
		// Update player's displayname.
		PlayerUtils.setDisplayName(player);
		
		event.setJoinMessage(player.getDisplayName() + ChatColor.GREEN + " logget på.");
	}
	
	@Override
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		// Update player's displayname.
		PlayerUtils.setDisplayName(player);
		
		event.setQuitMessage(player.getDisplayName() + ChatColor.RED + " logget av.");
	}
	
	@Override
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		
		event.setFormat(PlayerUtils.getChatName(player) + ": " + ChatColor.WHITE + event.getMessage());
	}
}
