package org.halvors.halvors.command;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class SpawnCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public SpawnCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.spawn")) {
				World world = player.getWorld();
				
				player.teleport(world.getSpawnLocation());
			}
		}
		
		return false;
	}
}
