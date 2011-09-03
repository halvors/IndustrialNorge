package org.halvors.halvors.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RepairUtils {
	@SuppressWarnings("deprecation")
	public static void repair(ItemStack item, Player player) {
		Material type = item.getType();
		
		if (item.getTypeId() > 0 && isTool(type) || isArmor(type)) {	
			item.setDurability((short) -1);
			player.updateInventory();
			player.setItemInHand(item);
		}
	}
	
	public static boolean isTool(Material type) {
		return type.equals(Material.WOOD_SWORD)
		|| type.equals(Material.WOOD_SPADE)
		|| type.equals(Material.WOOD_PICKAXE)
		|| type.equals(Material.WOOD_AXE)
		|| type.equals(Material.WOOD_HOE)
		|| type.equals(Material.STONE_SWORD)
		|| type.equals(Material.STONE_SPADE)
		|| type.equals(Material.STONE_PICKAXE)
		|| type.equals(Material.STONE_AXE)
		|| type.equals(Material.STONE_HOE)
		|| type.equals(Material.GOLD_SWORD)
		|| type.equals(Material.GOLD_SPADE)
		|| type.equals(Material.GOLD_PICKAXE)
		|| type.equals(Material.GOLD_AXE)
		|| type.equals(Material.GOLD_HOE)
		|| type.equals(Material.IRON_SWORD)
		|| type.equals(Material.IRON_SPADE)
		|| type.equals(Material.IRON_PICKAXE)
		|| type.equals(Material.IRON_AXE)
		|| type.equals(Material.IRON_HOE)
		|| type.equals(Material.DIAMOND_SWORD)
		|| type.equals(Material.DIAMOND_SPADE)
		|| type.equals(Material.DIAMOND_PICKAXE)
		|| type.equals(Material.DIAMOND_AXE)
		|| type.equals(Material.DIAMOND_HOE);
	}
	
	public static boolean isTool(ItemStack item) {
		return isTool(item.getType());
	}
	
	public static boolean isArmor(Material type) {
		return type.equals(Material.LEATHER_HELMET)
		|| type.equals(Material.LEATHER_CHESTPLATE)
		|| type.equals(Material.LEATHER_LEGGINGS)
		|| type.equals(Material.LEATHER_BOOTS)
		|| type.equals(Material.CHAINMAIL_HELMET)
		|| type.equals(Material.CHAINMAIL_CHESTPLATE)
		|| type.equals(Material.CHAINMAIL_LEGGINGS)
		|| type.equals(Material.CHAINMAIL_BOOTS)
		|| type.equals(Material.GOLD_HELMET)
		|| type.equals(Material.GOLD_CHESTPLATE)
		|| type.equals(Material.GOLD_LEGGINGS)
		|| type.equals(Material.GOLD_BOOTS)
		|| type.equals(Material.IRON_HELMET)
		|| type.equals(Material.IRON_CHESTPLATE)
		|| type.equals(Material.IRON_LEGGINGS)
		|| type.equals(Material.IRON_BOOTS)
		|| type.equals(Material.DIAMOND_HELMET)
		|| type.equals(Material.DIAMOND_CHESTPLATE)
		|| type.equals(Material.DIAMOND_LEGGINGS)
		|| type.equals(Material.DIAMOND_BOOTS);
	}
	
	public static boolean isArmor(ItemStack item) {
		return isArmor(item.getType());
	}
}
