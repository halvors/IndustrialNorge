package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.halvors.halvors.halvors;

public class HelpCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public HelpCommand(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("halvors.users.help")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.YELLOW + "Ikke ferdig ennå :(");
			}
		}
		
		return false;
	}
}
