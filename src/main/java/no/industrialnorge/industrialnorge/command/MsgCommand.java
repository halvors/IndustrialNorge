package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;
import no.industrialnorge.industrialnorge.util.ArmorUtils;
import no.industrialnorge.industrialnorge.util.PlayerUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class MsgCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public MsgCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length >= 2) {
				if (sender.hasPermission("industrialnorge.msg")) {
					Player target = PlayerUtils.getPlayer(args[1]);
					String msg = args[2];
					
					sender.sendMessage(ChatColor.DARK_AQUA);
					target.sendMessage();
				}
			}
		}
		
		return false;
	}
}
