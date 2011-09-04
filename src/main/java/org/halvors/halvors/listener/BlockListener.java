package org.halvors.halvors.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.halvors.halvors.halvors;
import org.halvors.halvors.database.BlockManager;
import org.halvors.halvors.util.RepairUtils;

public class BlockListener extends org.bukkit.event.block.BlockListener {
//	private final halvors plugin;
	private final BlockManager blockManager;
	
	public BlockListener(halvors plugin) {
//		this.plugin = plugin;
		this.blockManager = plugin.getBlockManager();
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			Block block = event.getBlock();
			Material type = block.getType();
			Player player = event.getPlayer();
			World world = block.getWorld();
			
			if (blockManager.ownBlock(block, player)) {
				blockManager.removeBlock(block);
			} else {
				Player owner = blockManager.getBlockOwner(block);
				
				player.sendMessage(ChatColor.RED + "Denne blokken eies av " + owner.getDisplayName() + ".");
			}
			
			// Infinite tools and armor.
			RepairUtils.repair(player.getItemInHand(), player);
			
			// Drop glass and glowstone.
			if (type.equals(Material.GLASS)) {
				world.dropItem(block.getLocation(), new ItemStack(Material.GLASS, 1));
			} else if (type.equals(Material.GLOWSTONE)) {
				block.setType(Material.AIR);
				world.dropItem(block.getLocation(), new ItemStack(Material.GLOWSTONE, 1));
				
				event.setCancelled(true);
			}
		}	
	}
	
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			Block block = event.getBlock();
			
			if (!blockManager.addBlock(block, player)) {
				event.setCancelled(true);
			}
		}
	}
}
