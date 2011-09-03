package org.halvors.halvors.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.halvors.halvors.halvors;

public class EntityListener extends org.bukkit.event.entity.EntityListener {
//	private final halvors plugin;
	
	public EntityListener(halvors plugin) {
//		this.plugin = plugin;
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
}
