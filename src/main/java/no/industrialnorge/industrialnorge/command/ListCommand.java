package no.industrialnorge.industrialnorge.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {
//	private final IndustrialNorge plugin;
	
	public ListCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("industrialnorge.list")) {
			sender.sendMessage("Spillere pålogget: " + getPlayerList());
			sender.sendMessage("Antall spillere: " + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers());
					
			return true;
		}
		
		return false;
	}
	
	private String getPlayerList() {
		StringBuilder playerList = new StringBuilder();

		for (Player player : Bukkit.getOnlinePlayers()) {
			if (playerList.length() >= 1) {
				playerList.append(ChatColor.WHITE);
				playerList.append(", ");
            }

			// Update player's displayname.
			PlayerUtils.setDisplayName(player);
			
			playerList.append(player.getDisplayName());
		}

		return playerList.toString();
	}
}
