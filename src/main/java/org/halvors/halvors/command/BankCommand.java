package org.halvors.halvors.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class BankCommand implements CommandExecutor {
//	private final halvors plugin;
//	private final Bank bank;
	
	public BankCommand(halvors plugin) {
//		this.plugin = plugin;
//		this.bank = plugin.getBank();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
//			Player player = (Player) sender;
			
			if (sender.hasPermission("halvors.bank")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.YELLOW + "Ikke ferdig ennå :(");
				}
			}
			
			/*
			if (sender.hasPermission("halvors.users.bank")) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.YELLOW + "Ikke ferdig ennå :(");
				} else {
					String subCommand = args[0];
				
					if (subCommand.equalsIgnoreCase("info")) {
						sender.sendMessage("Du har " + Integer.toString(bank.getBalance(player)) + " gull i banken.");
					} else if (subCommand.equalsIgnoreCase("inn")) {
						if (args.length >= 1) {
							int amount = Integer.parseInt(args[1]);
							ItemStack item = new ItemStack(Material.GOLD_INGOT, amount);
							
							sender.sendMessage("Du satte inn " + Integer.toString(amount) + " gull i banken.");
							player.getInventory().addItem(item);
							bank.deposit(player, amount);
						}
					}
				}
			}
			*/
		}
		
		return false;
	}
}
