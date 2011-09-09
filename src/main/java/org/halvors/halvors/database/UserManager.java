package org.halvors.halvors.database;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class UserManager {
	private final List<User> users;
	
	public UserManager() {
		this.users = new ArrayList<User>();
	}
	
	public User getUser(Player player) {
		for (User u : users) {
			if (player.equals(u.getPlayer())) {
				return u;
			}
		}
		
		return null;
	}
	
	public boolean hasUser(Player player) {
		return users.contains(player);
	}
	
	public void addUser(Player player) {
		if (!hasUser(player)) {
			// Create the user object.
			User user = new User(player);
			
			users.add(user);
		}
	}
	
	public void removeUser(Player player) {
		if (hasUser(player)) {
			users.remove(getUser(player));
		}
	}
}