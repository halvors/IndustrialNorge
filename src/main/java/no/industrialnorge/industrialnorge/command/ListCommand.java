package no.industrialnorge.industrialnorge.command;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {
	private final IndustrialNorge plugin;
	
	public ListCommand(IndustrialNorge plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
		
			if (sender.hasPermission("industrialnorge.list")) {
				if (args.length == 0) {
					StringBuilder onlinePlayers = new StringBuilder();
					onlinePlayers.append("Spillere pålogget: ");
					
					List<Player> players = Arrays.asList(plugin.getServer().getOnlinePlayers());
					
					boolean first = true;
					
					for (Player p : players) {
						if (!first) {
							onlinePlayers.append(", ");
						} else {
							first = false;
						}
						
						
						
						onlinePlayers.append(p.getDisplayName());
					}

					sender.sendMessage(onlinePlayers.toString());
					sender.sendMessage("Antall spillere: " + ChatColor.YELLOW + plugin.getServer().getOnlinePlayers().length + ChatColor.WHITE + "/" + ChatColor.RED + plugin.getServer().getMaxPlayers());
					
					return true;
				}
			}
		}
		
		return false;
	}
}
