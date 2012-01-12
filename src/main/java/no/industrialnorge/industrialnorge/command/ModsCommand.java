package no.industrialnorge.industrialnorge.command;

import java.util.List;

import net.minecraft.server.BaseMod;
import net.minecraft.server.ModLoader;
import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModsCommand implements CommandExecutor {
//	private final IndustrialNorge plugin;
	
	public ModsCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender instanceof Player) {
//			Player player = (Player) sender;
		
			if (sender.hasPermission("industrialnorge.list")) {
				if (args.length == 0) {
					@SuppressWarnings("unchecked")
					List<BaseMod> modsLoaded = ModLoader.getLoadedMods();
					
					StringBuilder modsList = new StringBuilder();
					modsList.append("Mods: ");
					
					
					boolean first = true;
					
					for (BaseMod m : modsLoaded) {
						if (!first) {
							modsList.append(", ");
						} else {
							first = false;
						}
						
						modsList.append(ChatColor.GREEN + m.toString() + ChatColor.WHITE);
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
}
