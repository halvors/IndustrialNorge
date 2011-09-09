package org.halvors.halvors.database;

import org.bukkit.entity.Player;

public class User {
	private final Player player;
	
	public User(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
