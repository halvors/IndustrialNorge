package no.industrialnorge.industrialnorge.command;

import java.util.List;

import net.minecraft.server.BaseMod;
import net.minecraft.server.ModLoader;
import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ModsCommand implements CommandExecutor {
//	private final IndustrialNorge plugin;
	
	public ModsCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command commad, String label, String[] args) {
		if (sender.hasPermission("industrialnorge.mods")) {
			sender.sendMessage("Mods: " + getModsList());
		} else {
			sender.sendMessage(ChatColor.RED + "Du har ikke rettigheter til å bruke denne kommandoen.");
		}
		
		return true;
	}

    @SuppressWarnings("unchecked")
	private String getModsList() {
        StringBuilder modsList = new StringBuilder();
        List<BaseMod> mods = ModLoader.getLoadedMods();

        for (BaseMod mod : mods) {
            if (modsList.length() >= 1) {
            	modsList.append(ChatColor.WHITE);
            	modsList.append(", ");
            }

            modsList.append(ChatColor.GREEN + mod.toString());
        }

        return modsList.toString();
    }
}
