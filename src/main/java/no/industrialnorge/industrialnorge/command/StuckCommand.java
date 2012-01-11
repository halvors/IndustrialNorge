package no.industrialnorge.industrialnorge.command;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StuckCommand implements CommandExecutor {
//	private final halvors plugin;
	
	public StuckCommand(IndustrialNorge plugin) {
//		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			World world = player.getWorld();
			
			if (player.hasPermission("halvors.stuck")) {
				Location pos = player.getLocation();
				Environment env = world.getEnvironment();
				Location newPos = null;
				
				if (env.equals(Environment.NORMAL)) {
					newPos = new Location(world, pos.getX(), world.getHighestBlockYAt(pos.getBlockX(), pos.getBlockZ()), pos.getZ());
				} else {
					sender.sendMessage(ChatColor.RED + "Denne kommandoen er ikke tilgjengelig i denne verden.");
				}
				
				if (world != null && newPos != null) {
					player.teleport(newPos);
				}
			}
		}
		
		return false;
	}
}
