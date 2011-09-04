package org.halvors.halvors.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.halvors.halvors.halvors;

public class BanCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public BanCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {		
		return false;
	}
}
