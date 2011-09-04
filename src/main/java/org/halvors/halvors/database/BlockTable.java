package org.halvors.halvors.database;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@Entity()
@Table(name = "halvors_block")
public class BlockTable {
    @Id
    private int id;
    private String owner;
    private double x;
    private double y;
    private double z;
    private String world;
    
    public BlockTable(Player player, Block block) {
    	setOwner(player.getName());
    	setLocation(block.getLocation());
    	setWorld(block.getWorld().getUID().toString());
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public double getX() {
    	return x;
    }
    
    public void setX(double x) {
    	this.x = x;
    }
    
    public double getY() {
    	return y;
    }
    
    public void setY(double y) {
    	this.y = y;
    }
    
    public double getZ() {
    	return z;
    }
    
    public void setZ(double z) {
    	this.z = z;
    }
    
    public Location getLocation() {
    	World world = Bukkit.getServer().getWorld(UUID.fromString(getWorld()));
    	
    	return new Location(world, x, y, z);
    }
    
    public void setLocation(Location location) {
    	this.x = location.getX();
    	this.y = location.getY();
    	this.z = location.getZ();
    }
    
    public String getWorld() {
    	return world;
    }
    
    public void setWorld(String world) {
    	this.world = world;
    }
}