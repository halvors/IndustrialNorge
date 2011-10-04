package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class LocCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public LocCommand(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
		
			if (sender.hasPermission("halvors.loc")) {
				if (args.length == 0) {
					Location loc = player.getLocation();
					
					sender.sendMessage(ChatColor.YELLOW + "Din nåværende posisjon er: x: " + loc.getBlockX() + ", y: " + loc.getBlockY() + ", z: " + loc.getBlockZ() + ".");
					
					return true;
				}
			}
		}
		
		return false;
	}
}
