package no.industrialnorge.industrialnorge;

import java.util.logging.Level;
import java.util.logging.Logger;

import no.industrialnorge.industrialnorge.command.ArmorCommand;
import no.industrialnorge.industrialnorge.command.LocCommand;
import no.industrialnorge.industrialnorge.command.SetSpawnCommand;
import no.industrialnorge.industrialnorge.command.SpawnCommand;
import no.industrialnorge.industrialnorge.command.StuckCommand;
import no.industrialnorge.industrialnorge.listener.PlayerListener;
import no.industrialnorge.industrialnorge.listener.VehicleListener;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class IndustrialNorge extends JavaPlugin {
    private final Logger logger = Logger.getLogger("Minecraft");
    
    private PluginManager pm;
    private PluginDescriptionFile desc;
    
	// Listeners
    private final PlayerListener playerListener = new PlayerListener(this);
    private final VehicleListener vehicleListener = new VehicleListener(this);
    
    private static IndustrialNorge instance;
    
    public IndustrialNorge() {
    	IndustrialNorge.instance = this;
    }
    
    @Override
    public void onDisable() {
        log(Level.INFO, "version " + getVersion() + " is disabled!");
    }
    
    @Override
    public void onEnable() {
        pm = getServer().getPluginManager();
        desc = getDescription();
     

        // Register our events.
        registerEvents();

		// Register our commands.
        registerCommands();
        
        log(Level.INFO, "version " + getVersion() + " is enabled!");
    }
    
    public void registerEvents() {
		// Player Events
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
		
		// Vehicle events
		pm.registerEvent(Event.Type.VEHICLE_UPDATE, vehicleListener, Event.Priority.Normal, this);
	}
	
	public void registerCommands() {
        getCommand("armor").setExecutor(new ArmorCommand(this));
//        getCommand("list").setExecutor(new ListCommand(this));
        getCommand("loc").setExecutor(new LocCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("stuck").setExecutor(new StuckCommand(this));
	}

    /**
     * Sends a console message.
     * 
     * @param level
     * @param msg
     */
    public void log(Level level, String msg) {
        logger.log(level, "[" + getName() + "] " + msg);
    }
    
    public static IndustrialNorge getInstance() {
        return instance;
    }
    
    /**
     * Get the name.
     * 
     * @return the plugin name
     */
    public String getName() {
        return desc.getName();
    }
    
    /**
     * Get the version.
     * 
     * @return the plugin version
     */
    public String getVersion() {
        return desc.getVersion();
    }
}