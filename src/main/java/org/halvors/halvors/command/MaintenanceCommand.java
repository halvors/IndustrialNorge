package org.halvors.halvors.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.PlayerUtils;

public class MaintenanceCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public MaintenanceCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("halvors.maintenance")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "Serveren er nÃ¥ i vedlikeholds modus.");
				
				for (Player player : Bukkit.getServer().getOnlinePlayers()) {
					if (!PlayerUtils.isAdmin(player.getName())) {
						player.kickPlayer("Serveren går ned for vedlikehold.");
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
}