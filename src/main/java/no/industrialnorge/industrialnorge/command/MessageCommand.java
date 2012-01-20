package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public MessageCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length >= 2) {
				if (sender.hasPermission("industrialnorge.msg")) {
					Player target = PlayerUtils.getPlayer(args[1]);
					String message = ChatColor.DARK_AQUA + "[" + player.getName() + "] til [" + target.getName() + "] ";
					
					for (int i = 0; i > 1; i++) {
						message += args[i] + " ";
					}
					
					sender.sendMessage(message);
					target.sendMessage(message);
					
					return true;
				}
			}
			
			if (args.length >= 2) {
				if (sender.hasPermission("industrialnorge.msg")) {
		            StringBuilder message = new StringBuilder();
		            
		            for (int i = 1; i < args.length; i++) {
		                message.append(args[i] + " ");
		            }
		            
		            Player target = PlayerUtils.getPlayer(args[0]);
		            
		            if (target != null) {
		                player.sendMessage(ChatColor.DARK_AQUA + "=> " + target.getName() + ": " + ChatColor.DARK_AQUA + message);
		                target.sendMessage(ChatColor.DARK_AQUA + "<= " + player.getName() + ": " + ChatColor.DARK_AQUA + message);
		            } else {
		                player.sendMessage(ChatColor.RED + "Brukeren er avlogget eller eksisterer ikke.");
		            }
				} else {
					player.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
				}
            }
		}
		
		return false;
	}
}
