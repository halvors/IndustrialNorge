package org.halvors.halvors.listener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.ConfigurationManager;
import org.halvors.halvors.util.WorldConfiguration;

public class EntityListener extends org.bukkit.event.entity.EntityListener {
//	private final halvors plugin;
	private final ConfigurationManager configManager;
	
	public EntityListener(halvors plugin) {
//		this.plugin = plugin;
		this.configManager = plugin.getConfigurationManager();
	}

	@Override
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (!event.isCancelled()) {
			Entity entity = event.getEntity();
			World world = entity.getWorld();
			WorldConfiguration worldConfig = configManager.get(world);
			
			// Prevent creatures that not is set allowed to spawn.
			CreatureType type = event.getCreatureType();
			
			if (worldConfig.blockCreatureSpawn.contains(type)) {
				event.setCancelled(true);
			}
		}
	}
	
	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		if (!event.isCancelled()) {
			Entity entity = event.getEntity();
			
			if (entity instanceof Player) {
				event.setCancelled(true);
			}
		}
	}
	
	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		World world = entity.getWorld();
		
		if (entity instanceof Cow) {
			// Make wolf drop bone.
			ItemStack item = new ItemStack(Material.BONE, 2);
			world.dropItemNaturally(entity.getLocation(), item);
		}
	}
	
	@Override
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (event.isCancelled()) {
			Entity entity = event.getEntity();
			
			if (entity instanceof Player) {
				Player player = (Player) entity;
				
				// Hindre foodbar i å synke.
				if (player.getFoodLevel() < 20) {
					player.setFoodLevel(20);
				}
				
				event.setCancelled(true);
			}
		}
	}
}
