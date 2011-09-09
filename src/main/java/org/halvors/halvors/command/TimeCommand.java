package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class TimeCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public TimeCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 0) {
				
			} else {
				String subCommand = args[0];
				
				if (sender.hasPermission("halvors.users.time")) {
					if (subCommand.equalsIgnoreCase("day") || subCommand.equalsIgnoreCase("dag")) {
						player.setPlayerTime(0, false);
				
						sender.sendMessage(ChatColor.YELLOW + "Tid ble forandret til dag.");
				
						return true;
					} else if (subCommand.equalsIgnoreCase("night") || subCommand.equalsIgnoreCase("natt")) {
						player.setPlayerTime(14000, false);
					
						sender.sendMessage(ChatColor.YELLOW + "Tid ble forandret til natt.");
				
						return true;
					} else if (subCommand.equalsIgnoreCase("back") || subCommand.equalsIgnoreCase("tilbake")) {
						sender.sendMessage(ChatColor.YELLOW + "Du er nå tilbake i riktig døgnrytme :P");
					
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
