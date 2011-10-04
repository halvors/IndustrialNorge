package org.halvors.halvors;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.halvors.halvors.command.ArmorCommand;
import org.halvors.halvors.command.BankCommand;
import org.halvors.halvors.command.CiCommand;
import org.halvors.halvors.command.HelpCommand;
import org.halvors.halvors.command.LocCommand;
import org.halvors.halvors.command.MaintenanceCommand;
import org.halvors.halvors.command.SetSpawnCommand;
import org.halvors.halvors.command.SpawnCommand;
import org.halvors.halvors.command.StuckCommand;
import org.halvors.halvors.command.TimeCommand;
import org.halvors.halvors.listener.BlockListener;
import org.halvors.halvors.listener.EntityListener;
import org.halvors.halvors.listener.PlayerListener;
import org.halvors.halvors.listener.VehicleListener;
import org.halvors.halvors.util.ConfigurationManager;

public class halvors extends JavaPlugin {
    private final Logger logger = Logger.getLogger("Minecraft");
    
    private PluginManager pm;
    private PluginDescriptionFile desc;
    
    // Configuration
    private final ConfigurationManager configManager = new ConfigurationManager(this);
	
	// Listeners
    private final BlockListener blockListener = new BlockListener(this);
    private final EntityListener entityListener = new EntityListener(this);
    private final PlayerListener playerListener = new PlayerListener(this);
    private final VehicleListener vehicleListener = new VehicleListener(this);
    
    private static halvors instance;
    
    public halvors() {
    	halvors.instance = this;
    }
    
    @Override
    public void onDisable() {
        // Save configuration.
        configManager.unload();
        
        log(Level.INFO, "version " + getVersion() + " is disabled!");
    }
    
    @Override
    public void onEnable() {
        pm = getServer().getPluginManager();
        desc = getDescription();
        
        // Load configuration.
        configManager.load();

        // Register our events.
        registerEvents();

		// Register our commands.
        registerCommands();
        
        // Register our recipes.
        registerRecipes();
        
        log(Level.INFO, "version " + getVersion() + " is enabled!");
    }
    
    public void registerEvents() {
		// Block Events
		pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
		
		// Entity events
		pm.registerEvent(Event.Type.CREATURE_SPAWN, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.FOOD_LEVEL_CHANGE, entityListener, Event.Priority.Normal, this);
		
		// Player Events
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		
		// Vehicle events
		pm.registerEvent(Event.Type.VEHICLE_UPDATE, vehicleListener, Event.Priority.Normal, this);
		
		// World events
		
		// Weather events
	}
	
	public void registerCommands() {
        getCommand("armor").setExecutor(new ArmorCommand(this));
//		getCommand("ban").setExecutor(new BanCommand(this));
		getCommand("bank").setExecutor(new BankCommand(this));
		getCommand("ci").setExecutor(new CiCommand(this));
        getCommand("help").setExecutor(new HelpCommand(this));
//		getCommand("kick").setExecutor(new KickCommand(this));
        getCommand("loc").setExecutor(new LocCommand(this));
		getCommand("maintenance").setExecutor(new MaintenanceCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("stuck").setExecutor(new StuckCommand(this));
		getCommand("time").setExecutor(new TimeCommand(this));
	}
	
	public void registerRecipes() {
//		// Smelt clayblokker til brick.
//		FurnaceRecipe clayToBrickFurnaceRecipe = new FurnaceRecipe(new ItemStack(Material.BRICK, 1), Material.CLAY);
//	    getServer().addRecipe(clayToBrickFurnaceRecipe);
//	    
//	    // Lag clayballer av clayblokk.
//	    ShapedRecipe clayRecipe = new ShapedRecipe(new ItemStack(Material.CLAY_BALL, 4));
//	    clayRecipe.shape(new String[] { "c" });
//	    clayRecipe.setIngredient('c', Material.CLAY);
//	    getServer().addRecipe(clayRecipe);
//	        
//	    // Lag snøballer av snøblokk.
//	    ShapedRecipe snowRecipe = new ShapedRecipe(new ItemStack(Material.SNOW_BALL, 4));
//	    clayRecipe.shape(new String[] { "s" });
//	    clayRecipe.setIngredient('s', Material.SNOW_BLOCK);
//	    getServer().addRecipe(snowRecipe);
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
    
    public static halvors getInstance() {
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
    
    /**
     * Get the ConfigurationManager.
     * 
     * @return the ConfigurationManager
     */
    public ConfigurationManager getConfigurationManager() {
        return configManager;
    }
}