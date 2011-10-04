package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class GamemodeCommand implements CommandExecutor {

//	private final halvors plugin;
	
	public GamemodeCommand(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("halvors.gamemode")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				GameMode gameMode = null;
				
				if (args.length == 0) {
					String subCommand = args[0];
					
					if (subCommand.equalsIgnoreCase("Survival")) {
						gameMode = GameMode.SURVIVAL;
					} else if (subCommand.equalsIgnoreCase("Creative")) {
						gameMode = GameMode.CREATIVE;
					}
					
					if (gameMode != null) {
						sender.sendMessage(ChatColor.YELLOW + "Ditt spillmodus er nå " + gameMode.name() + ".");
						
						player.setGameMode(gameMode);
					}
				} else if (args.length == 1) {
					
				}
				
				return true;
			}
		}
		
		return false;
	}
}
