package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class CiCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public CiCommand(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 0) {
				if (sender.hasPermission("halvors.user.ci")) {
					player.getInventory().clear();
					sender.sendMessage(ChatColor.GREEN + "Poff! Dine items forsvant i evigheten.");
				}
				
				return true;
			}
		}
		
		return true;
	}
}