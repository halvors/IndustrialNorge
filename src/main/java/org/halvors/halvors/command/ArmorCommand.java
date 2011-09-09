package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.ArmorUtils;

public class ArmorCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public ArmorCommand(halvors plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.admin.armor")) {	
				ItemStack newHelmet = player.getItemInHand();
				Material type = newHelmet.getType();
				
				if (ArmorUtils.isAllowed(type)) {
					PlayerInventory inventory = player.getInventory();
					ItemStack helmet = inventory.getHelmet();
					
					if (helmet.getAmount() > 0) {
						inventory.setHelmet(null);
						inventory.addItem(new ItemStack(helmet.getType(), 1));
					}
					
					inventory.setHelmet(new ItemStack(type, 1));
					newHelmet.setAmount(newHelmet.getAmount() - 1);
					
					sender.sendMessage(ChatColor.YELLOW + "Du har nå en " + type.toString() + " på hodet.");
				} else {
					sender.sendMessage(ChatColor.RED + "Dette er ikke en hatt.");
				}
			}
		}
		
		return false;
	}
}
