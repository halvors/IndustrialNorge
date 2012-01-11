package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.Bukkit;
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
					
					boolean first = true;
					
					for (Player p : plugin.getServer().getOnlinePlayers()) {
						if (!first) {
							onlinePlayers.append(", ");
						} else {
							first = false;
						}
						
						onlinePlayers.append(p.getDisplayName());
					}

					sender.sendMessage("Spiller på: " +  onlinePlayers);
					sender.sendMessage("Spillere pålogget: " + plugin.getServer().getOnlinePlayers().length);
					
					return true;
				}
			}
		}
		
		return false;
	}
}
