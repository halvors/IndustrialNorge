package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public LocCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
		
			if (sender.hasPermission("industrialnorge.loc")) {
				Location loc = player.getLocation();
					
				sender.sendMessage(ChatColor.YELLOW + "Din nåværende posisjon er: x: " + loc.getBlockX() + ", y: " + loc.getBlockY() + ", z: " + loc.getBlockZ() + ".");
				
				return true;
			}
		}
		
		return false;
	}
}
