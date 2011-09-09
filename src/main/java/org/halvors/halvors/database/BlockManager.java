package org.halvors.halvors.database;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class BlockManager {
	private final halvors plugin;
	
	private static BlockManager instance;
	
	public BlockManager(halvors plugin) {
		this.plugin = plugin;
		
		this.instance = this;
	}
	
	public static BlockManager getInstance() {
		return instance;
	}
	
	public BlockTable getBlockTable(Block block) {
		Location location = block.getLocation();
		
        return plugin.getDatabase().find(BlockTable.class).where()
            .eq("x", location.getBlockX()).eq("y", location.getBlockY()).eq("z", location.getBlockZ()).ieq("world", location.getWorld().getUID().toString()).findUnique();
    }
	
	public List<BlockTable> getBlockTables(World world) {
		return plugin.getDatabase().find(BlockTable.class).where().ieq("world", world.getUID().toString()).findList();
	}
	
	public List<BlockTable> getBlockTables(Player player) {
		List<BlockTable> bts = new ArrayList<BlockTable>();
		
		for (BlockTable bt : getBlockTables(player.getWorld())) {
			if (bt.getOwner().equals(player.getName())) {
				bts.add(bt);
			}
		}
		
		return bts;
	}
	
	public boolean addBlock(Block block, Player player) {
		if (!hasBlock(block)) {
			// Create the BlockTable instance.
			BlockTable bt = new BlockTable(player, block);
			
			// Save it to the database.
			plugin.getDatabase().save(bt);
			
			return true;
		}
		
		return false;
	}
	
	public boolean removeBlock(Block block) {
		if (hasBlock(block)) {
			BlockTable bt = getBlockTable(block);
			
			if (bt != null) {
				plugin.getDatabase().delete(bt);
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasBlock(Block block) {
		BlockTable bt = getBlockTable(block);
		
		return bt != null;
	}
	
	public boolean ownBlock(Block block, Player player) {
		BlockTable bt = getBlockTable(block);
		
		return bt.getOwner().equalsIgnoreCase(player.getName());
	}
	
	public Player getBlockOwner(Block block) {
		BlockTable bt = getBlockTable(block);
		Player player = Bukkit.getServer().getPlayer(bt.getOwner());
		
		return player;
	}
	
	public void setBlockOwner(Block block, Player player) {
		BlockTable bt = getBlockTable(block);
		
		if (bt != null) {
			bt.setOwner(Player player);
		}
	}
}
