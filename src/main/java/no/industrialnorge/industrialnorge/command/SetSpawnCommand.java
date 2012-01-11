package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public SetSpawnCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.setspawn")) {
				if (args.length == 0) {
					World world = player.getWorld();
					Location loc = player.getLocation();
				
					sender.sendMessage(ChatColor.YELLOW + "Spawn er n� satt til: x: " + loc.getBlockX() + ", y: " + loc.getBlockY() + ", z: " + loc.getBlockY() + ".");
					
					world.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
					
					return true;
				}
			}
		}
		
		return false;
	}
}
