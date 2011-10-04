package org.halvors.halvors.command;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class SetSpawnCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public SetSpawnCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.setspawn")) {
				World world = player.getWorld();
				Location loc = player.getLocation();
				
				world.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			}
		}
		
		return false;
	}
}
