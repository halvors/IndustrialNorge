package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public HelpCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("industrialnorge.help")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.YELLOW + "Ikke ferdig ennå :(");
				
				return true;
			}
		}
		
		return false;
	}
}
