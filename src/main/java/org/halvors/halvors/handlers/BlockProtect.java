package org.halvors.halvors.handlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.halvors.halvors.halvors;

public class BlockProtect {
	private final halvors plugin;
	
	private MySQLHandler sqlHandler;
	private Connection conn;
	private PreparedStatement add;
	private PreparedStatement isProtected;
	private PreparedStatement getOwner;
	
	public BlockProtect(halvors plugin) {
		this.plugin = plugin;
		this.sqlHandler = plugin.getSqlHandler();
	}
	
	public void initialize() {
		conn = sqlHandler.getConnection();
		
		try {
			add = conn.prepareStatement("REPLACE INTO `blocks` (`name`, `x`, `y`, `z`, `world`) VALUES (?, ?, ?, ?, ?)");
			isProtected = conn.prepareStatement("SELECT name FROM blocks WHERE x=? AND y=? AND z=? AND world=?");
			getOwner = conn.prepareStatement("SELECT name FROM blocks WHERE x=? AND y=? AND z=? AND world=?");
		} catch (SQLException e) {
			plugin.log(Level.SEVERE, "Kunne ikke initialisere prepared statements for BlockProtectHandler.");
		}
	}
	
	public void exit() {
		try {
			add.close();
			isProtected.close();
			getOwner.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public boolean add(Player player, Block block) {
		try {
			add.setString(1, player.getName());
			add.setShort(2, (short) block.getX());
			add.setShort(3, (short) block.getY());
			add.setShort(4, (short) block.getZ());
			add.setString(5, block.getWorld().getName());
			add.executeUpdate();

			return true;
		} catch (SQLException e) {
			plugin.log(Level.SEVERE, "MySQL Error: "+ Thread.currentThread().getStackTrace()[0].getMethodName());
			player.sendMessage("Kunne ikke beskytte blokken, prøv igjen eller kontakt Admin!");
			
			return false;
		}
	}
	
	public boolean delete(Player player, Block block) {
		boolean deleted = false;
		
		if (isProtected(block)) {
			String owner = getOwner(block);
			
			if (player.getName() == owner) {
				if (sqlHandler.update("DELETE FROM blocks WHERE x='" + block.getX() + "' AND y='" + block.getY() + "' AND z='" + block.getZ() + "' AND world='" + block.getWorld().getName() + "'")) {
					deleted = true;
				} else {
					deleted = false;
				}
			} else {
				player.sendMessage("Denne blokken eies av: " + owner);
			}
		} else {
			// Blokken er ikke beskyttet så sender kall om å fjerne den
			// Husk å legge til logg etterhvert.
			deleted = true;
		}
		
		return deleted;
	}
	
	public boolean delete(Block block) {
		if (sqlHandler.update("DELETE FROM blocks WHERE x='" + block.getX() + "' AND y='" + block.getY() + "' AND z='" + block.getZ() + "' AND world='" + block.getWorld().getName() + "'")) {
			return true;
		}
		
		return false;
	}
	
	public String getOwner(Block block) {
		String name = null;
		
		try {
			getOwner.setShort(1, (short) block.getX());
			getOwner.setShort(2, (short) block.getY());
			getOwner.setShort(3, (short) block.getZ());
			getOwner.setString(4, block.getWorld().getName());
			ResultSet rs = getOwner.executeQuery();
			
			while(rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			plugin.log(Level.SEVERE, "MySQL Error: "+ Thread.currentThread().getStackTrace()[0].getMethodName());
		}
		
		return name;
	}
	
	public boolean isProtected(Block block) {
		boolean prot = false;
		
		try {
			isProtected.setShort(1, (short) block.getX());
			isProtected.setShort(2, (short) block.getY());
			isProtected.setShort(3, (short) block.getZ());
			isProtected.setString(4, block.getWorld().getName());
			ResultSet rs = isProtected.executeQuery();
			
			while(rs.next()) {
				prot = true;
			}
		} catch (SQLException e) {
			plugin.log(Level.SEVERE, "MySQL Error: "+ Thread.currentThread().getStackTrace()[0].getMethodName());
		}
		
		return prot;
	}
}