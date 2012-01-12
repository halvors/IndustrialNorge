package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CiCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public CiCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Inventory inventory = null;
			
			if (args.length == 0) {
				if (sender.hasPermission("industrialnorge.ci")) {
					inventory = player.getInventory();
					
					sender.sendMessage(ChatColor.GREEN + "Poff! Dine items forsvant i evigheten.");
				}
			} else if (args.length == 1) {
				if (sender.hasPermission("industrialnorge.ci.others")) {
					Player target = PlayerUtils.getPlayer(args[1]);
					inventory = target.getInventory();
					
					target.sendMessage(ChatColor.GREEN + "Poff! Dine items ble fjernet av en Vakt/Stab og forsvant i evigheten.");
				}
			}
			
			if (inventory != null) {
				inventory.clear();
			}
			
			return true;
		}
		
		return false;
	}
}