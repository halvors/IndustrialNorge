package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {
//	private final IndustrialNorge plugin;
	
	public ListCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
//			Player player = (Player) sender;
		
			if (sender.hasPermission("industrialnorge.list")) {
				if (args.length == 0) {
					StringBuilder onlinePlayers = new StringBuilder();
					onlinePlayers.append("Spillere pålogget: ");
					
					boolean first = true;
					
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (!first) {
							onlinePlayers.append(", ");
						} else {
							first = false;
						}
						
						onlinePlayers.append(p.getDisplayName());
					}

					sender.sendMessage(onlinePlayers.toString());
					sender.sendMessage("Antall spillere: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().length + ChatColor.WHITE + "/" + ChatColor.RED + Bukkit.getMaxPlayers());
					
					return true;
				}
			}
		}
		
		return false;
	}
}
