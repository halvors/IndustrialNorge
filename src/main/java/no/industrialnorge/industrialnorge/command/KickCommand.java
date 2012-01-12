package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public KickCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (args.length >= 1) {
			if (sender.hasPermission("industrialnorge.kick")) {
				Player kickPlayer = PlayerUtils.getPlayer(args[1]);
				String senderName = sender instanceof Player ? ((Player) sender).getDisplayName() : ChatColor.GOLD + "Console";
				String kickReason = args.length > 1 ? args[3] : "The banhammar has spoken!";
				
				Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + kickPlayer.getDisplayName() + ChatColor.GREEN + " ble kicket for " + kickReason + " av " + senderName);
				kickPlayer.kickPlayer(kickReason);
				kickPlayer.getWorld().strikeLightningEffect(kickPlayer.getLocation());
					
				return true;
			}
		}
		
		return false;
	}
}