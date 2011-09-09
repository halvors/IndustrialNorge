package org.halvors.halvors.util;

import org.bukkit.entity.CreatureType;

public class CreatureUtils {
	public static boolean isAllowed(CreatureType type) {
		return type.equals(CreatureType.CHICKEN)
			|| type.equals(CreatureType.COW)
			|| type.equals(CreatureType.SHEEP)
			|| type.equals(CreatureType.SLIME)
			|| type.equals(CreatureType.SQUID)
			|| type.equals(CreatureType.WOLF);
	}
}
