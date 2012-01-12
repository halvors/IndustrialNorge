package no.industrialnorge.industrialnorge.command;

import java.util.List;

import net.minecraft.server.BaseMod;
import net.minecraft.server.ModLoader;
import net.minecraft.server.ModLoaderMp;
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
		if (sender.hasPermission("industrialnorge.list")) {
			sender.sendMessage("Mods: " + getModsList());
				
			return true;
		}
		
		return false;
	}

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
