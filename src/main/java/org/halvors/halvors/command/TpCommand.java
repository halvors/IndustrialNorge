package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.PlayerUtils;

public class TpCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public TpCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 1) {
				if (sender.hasPermission("halvors.admin.tp")) {
					Player target = PlayerUtils.getPlayer(args[0]);
					
					if (target != null) {
						player.teleport(target.getLocation());
						sender.sendMessage(ChatColor.GREEN + "Du ble teleportert til " + target.getDisplayName() + ChatColor.GREEN + ".");
					} else {
						sender.sendMessage(ChatColor.RED + "Fant ikke brukeren du ville teleportere til.");
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
}