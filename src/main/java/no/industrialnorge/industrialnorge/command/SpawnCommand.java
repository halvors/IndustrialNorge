package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public SpawnCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("industrialnorge.spawn")) {
				if (args.length == 0) {
					World world = player.getWorld();
				
					player.teleport(world.getSpawnLocation());
					sender.sendMessage(ChatColor.YELLOW + "Woosh! Du ble sendt til spawn.");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
			}
			
			return true;
		}
		
		return false;
	}
}
