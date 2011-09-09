package org.halvors.halvors.database;

import org.bukkit.Location;

public class Block {
	public BlockTable getBlockTable(Block block) {
		Location loc = block.getLocation();
		
        return plugin.getDatabase().find(BlockTable.class).where()
            .eq("x", loc.getBlockX()).eq("y", loc.getBlockY()).eq("z", loc.getBlockZ()).ieq("world", loc.getWorld().getUID().toString()).findUnique();
    }
}
