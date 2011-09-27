package org.halvors.halvors.listener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.halvors.halvors.halvors;
import org.halvors.halvors.util.CreatureUtils;
import org.halvors.halvors.util.RepairUtils;

public class EntityListener extends org.bukkit.event.entity.EntityListener {
//	private final halvors plugin;
	
	public EntityListener(halvors plugin) {
//		this.plugin = plugin;
	}

	@Override
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (!event.isCancelled()) {
			CreatureType type = event.getCreatureType();
			
			// Prevent creatures that not is set allowed to spawn.
			if (!CreatureUtils.isAllowed(type)) {
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
			
			if (event instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) event;
				Entity damager = edbee.getDamager();
				
				if (damager instanceof Player) {
					Player player = (Player) damager;
						
					// Infinite tools and armor.
					RepairUtils.repair(player.getItemInHand(), player);
				}
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
				
				if (player.getFoodLevel() < 20) {
					player.setFoodLevel(20);
				}
				
			}
		}
	}
}
