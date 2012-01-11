package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public TpCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length == 1) {
				if (sender.hasPermission("halvors.tp")) {
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