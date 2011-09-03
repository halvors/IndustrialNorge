package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.PlayerUtils;

public class KickCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public KickCommand(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.users.kick")) {
				if (args.length == 2) {
					Player kickPlayer = PlayerUtils.getPlayer(args[1]);
					kickPlayer.kickPlayer(kickPlayer.getName());
					player.getWorld().strikeLightningEffect(kickPlayer.getLocation());
				
					sender.sendMessage(ChatColor.GREEN + "Player " + ChatColor.YELLOW + kickPlayer.getDisplayName() + ChatColor.GREEN + " ble kicket av " + ChatColor.YELLOW + player.getDisplayName());
				
					return true;
				}
			}
		}
		
		return false;
	}
}
