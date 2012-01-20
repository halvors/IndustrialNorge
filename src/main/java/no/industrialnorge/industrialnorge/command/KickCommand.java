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
				Player target = PlayerUtils.getPlayer(args[1]);
				String senderName = sender instanceof Player ? ((Player) sender).getDisplayName() : ChatColor.GOLD + "Console";
				String kickReason = args.length > 1 ? args[3] : "The banhammar has spoken!";
				
				if (target != null) {
					// Kick the player.
					target.kickPlayer(kickReason);
					target.getWorld().strikeLightningEffect(target.getLocation());
				
					// And notify other players.
					for (Player player : Bukkit.getOnlinePlayers()) {
						if (player.hasPermission("industrialnorge.kick.notify")) {
							player.sendMessage(target.getDisplayName() + ChatColor.WHITE + " ble kicket for " + kickReason + " av " + senderName);
						}
					}
				} else {
					// TODO: Legg til en for for feilmelding her.
				}
					
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
			}
			
			return true;
		}
		
		return false;
	}
}
