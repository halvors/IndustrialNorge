package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class StuckCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public StuckCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			World world = player.getWorld();
			
			if (player.hasPermission("halvors.users.stuck")) {
				Location pos = player.getLocation();
				
				if (world.getEnvironment().equals(Environment.NORMAL)) {
					int y = world.getHighestBlockYAt(pos.getBlockX(), pos.getBlockZ());
				
					player.teleport(new Location(world, pos.getX(), y + 1, pos.getZ()));
				
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Denne kommandoen er ikke tilgjengelig i denne verden.");
				}
			}
		}
		
		return false;
	}
}
