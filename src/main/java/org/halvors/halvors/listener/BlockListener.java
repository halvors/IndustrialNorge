package org.halvors.halvors.listener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.halvors.halvors.halvors;
import org.halvors.halvors.handlers.BlockProtect;
import org.halvors.halvors.util.RepairUtils;

public class BlockListener extends org.bukkit.event.block.BlockListener {
//	private final halvors plugin;
	private final BlockProtect blockProtect;
	
	public BlockListener(halvors plugin) {
//		this.plugin = plugin;
		this.blockProtect = plugin.getBlockProtect();
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			Player player = event.getPlayer();
			Block block = event.getBlock();
			Material type = block.getType();
			World world = block.getWorld();

			// Infinite tools and armor.
			RepairUtils.repair(player.getItemInHand(), player);
			
			// Kreves det tillatelse for bygging og kan brukeren bygge?
			if (!player.hasPermission("halvors.user.build")) {
				event.setCancelled(true);
				
				return;
			} else {
				if (!blockProtect.delete(player, block)) {
					event.setCancelled(true);
				
					return;
				}
			}

			// Glassblokker dropper glass-item.
			if (type.equals(Material.GLASS)) {
				world.dropItemNaturally(block.getLocation(), new ItemStack(Material.GLASS, 1));
			}

			// Glowstone-blokker dropper glowstone-item i stedet for glowstone dust.
			if (type.equals(Material.GLOWSTONE)) {
				event.setCancelled(true);
				block.setType(Material.AIR);
				world.dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE, 1));
			}
		}
	}
	
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();

		// Kreves det tillatelse for bygging og kan brukeren bygge?
		if (!player.hasPermission("halvors.users.build")) {
			event.setCancelled(true);
		} else {
			if (!blockProtect.add(player, block)) {
				event.setBuild(false);
				event.setCancelled(true);
			}
		}
	}
}
