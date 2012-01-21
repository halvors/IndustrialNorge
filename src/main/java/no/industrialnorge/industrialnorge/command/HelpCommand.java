package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {
	private final IndustrialNorge plugin;
	
	public HelpCommand(IndustrialNorge plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("industrialnorge.help")) {
			sender.sendMessage(ChatColor.YELLOW + plugin.getName() + ChatColor.GREEN + " (" + ChatColor.WHITE + plugin.getVersion() + ChatColor.GREEN + ")");
	        sender.sendMessage(ChatColor.RED + "[]" + ChatColor.WHITE + " Required, " + ChatColor.GREEN + "<>" + ChatColor.WHITE + " Optional.");

	        sender.sendMessage("/help" + ChatColor.YELLOW + " - Viser hjelp menyen.");
	        
	        if (sender.hasPermission("industrialnorge.armor")) {
	        	sender.sendMessage("/armor" + ChatColor.YELLOW + " - Lar deg sette blokker og items på hodet.");
	        }
	        
	        if (sender.hasPermission("industrialnorge.ci.others")) {
	        	sender.sendMessage("/ci" + ChatColor.GREEN + "<" + ChatColor.WHITE + "player" + ChatColor.GREEN + ">" + ChatColor.YELLOW + " - Fjerner alt i ditt inventory eller andres.");
	        } else if (sender.hasPermission("industrialnorge.ci")) {
	        	sender.sendMessage("/ci" + " - Fjerner alt i ditt inventory.");
	        }
	        
	        if (sender.hasPermission("industrialnorge.list")) {
	        	sender.sendMessage("/list" + ChatColor.YELLOW + " - Viser en liste med påloggede spillere.");
	        }
	        
	        if (sender.hasPermission("industrialnorge.loc")) {
	        	sender.sendMessage("/loc" + ChatColor.YELLOW + " - Viser din nåværende posisjon.");
	        }
	        
	        if (sender.hasPermission("industrialnorge.spawn")) {
	        	sender.sendMessage("/spawn" + ChatColor.YELLOW + " - Teleporterer deg til din verdens spawn.");
	        }
	        
	        if (sender.hasPermission("industrialnorge.stuck")) {
	        	sender.sendMessage("/stuck" + ChatColor.YELLOW + " - Flytter deg til høyeste blokk i verden, nyttig om du er stuck inni en blokk.");
	        }
		} else {
			sender.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
		}
		
		return true;
	}
}
