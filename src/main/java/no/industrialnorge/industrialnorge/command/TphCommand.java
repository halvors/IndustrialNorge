package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TphCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public TphCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 1) {
				if (sender.hasPermission("industrialnorge.tph")) {
					Player target = PlayerUtils.getPlayer(args[0]);
				
					if (target != null) {
						target.teleport(player.getLocation());
						sender.sendMessage(ChatColor.GREEN + "Du teleporterte " + target.getDisplayName() + ChatColor.GREEN + " til deg.");
						target.sendMessage(ChatColor.GREEN + "Du ble teleportert til " + player.getDisplayName() + ChatColor.GREEN + ".");
					} else {
						sender.sendMessage(ChatColor.RED + "Fant ikke brukeren du ville teleportere til.");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
				}
				
				return true;
			}
			
			return true;
		}
		
		return false;
	}
}