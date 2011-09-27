package org.halvors.halvors;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.halvors.halvors.command.ArmorCommand;
import org.halvors.halvors.command.BanCommand;
import org.halvors.halvors.command.CiCommand;
import org.halvors.halvors.command.HelpCommand;
import org.halvors.halvors.command.KickCommand;
import org.halvors.halvors.command.SetSpawnCommand;
import org.halvors.halvors.command.SpawnCommand;
import org.halvors.halvors.command.StuckCommand;
import org.halvors.halvors.command.TimeCommand;
import org.halvors.halvors.command.TpCommand;
import org.halvors.halvors.command.TphCommand;
import org.halvors.halvors.handlers.MySQLHandler;
import org.halvors.halvors.listener.BlockListener;
import org.halvors.halvors.listener.EntityListener;
import org.halvors.halvors.listener.PlayerListener;
import org.halvors.halvors.listener.VehicleListener;
import org.halvors.halvors.sql.MySQLConnector;
import org.halvors.halvors.sql.MySQLObject;
import org.halvors.halvors.util.ConfigurationManager;

public class halvors extends JavaPlugin {
    private final Logger logger = Logger.getLogger("Minecraft");
    
    private PluginManager pm;
    private PluginDescriptionFile desc;
    
    // Configuration
    private final ConfigurationManager configManager = new ConfigurationManager(this);

    // MySQL
	private MySQLConnector sqlConnector = new MySQLConnector(this);
	private MySQLHandler sqlHandler = new MySQLHandler(this);
	private MySQLObject sqlObject = new MySQLObject(this);
    
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
        
        sqlConnection();

		// Opprett tabeller som ikke finnes.
		sqlHandler.update("CREATE TABLE IF NOT EXISTS `blocklog` (`uid` int(11) NOT NULL,`x` smallint(6) NOT NULL,`y` smallint(6) NOT NULL,`z` smallint(6) NOT NULL,`action` tinyint(1) NOT NULL,`world` varchar(50) NOT NULL,`data` varchar(255) NOT NULL,`time` int(11) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1");
		sqlHandler.update("CREATE TABLE IF NOT EXISTS `blocks` ( `uid` int(11) NOT NULL, `x` int(6) NOT NULL, `y` int(6) NOT NULL, `z` int(6) NOT NULL, `world` varchar(50) NOT NULL, UNIQUE KEY `location` (`x`,`y`,`z`,`world`)) ENGINE=InnoDB DEFAULT CHARSET=latin1");
		sqlHandler.update("CREATE TABLE IF NOT EXISTS `log` (`id` int(11) NOT NULL AUTO_INCREMENT,`pid` int(6) NOT NULL,`vid` int(6) NOT NULL,`aid` int(3) NOT NULL,`amount` int(11) NOT NULL DEFAULT '0',`data` varchar(255) NOT NULL,`time` int(11) NOT NULL,PRIMARY KEY (`id`)) ENGINE=MyISAM DEFAULT CHARSET=latin1");
		sqlHandler.update("CREATE TABLE IF NOT EXISTS `users` (`id` int(6) NOT NULL AUTO_INCREMENT,`name` varchar(16) NOT NULL,`status` int(2) NOT NULL DEFAULT '0',`active` int(11) NOT NULL,`last_login` int(11) NOT NULL,PRIMARY KEY (`id`),UNIQUE KEY `name` (`name`)) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=latin1");
		sqlHandler.update("CREATE TABLE IF NOT EXISTS `objects` (`id` int(11) NOT NULL AUTO_INCREMENT, `name` varchar(255) NOT NULL, `type` int(3) NOT NULL, `object` blob NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");

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
		pm.registerEvent(Event.Type.BLOCK_DAMAGE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_IGNITE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PHYSICS, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_FROMTO, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BURN, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_FORM, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PISTON_EXTEND, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PISTON_RETRACT, blockListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.SIGN_CHANGE, blockListener, Event.Priority.Normal, this);

		// Entity events
		pm.registerEvent(Event.Type.CREATURE_SPAWN, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_COMBUST, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_INTERACT, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_TARGET, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENDERMAN_PICKUP, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENDERMAN_PLACE, entityListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PIG_ZAP, entityListener, Event.Priority.Normal, this);
		
		// Player Events
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
		
		// Vehicle events
		pm.registerEvent(Event.Type.VEHICLE_UPDATE, vehicleListener, Event.Priority.Normal, this);
		
		// World events
		
		// Weather events
	}
	
	public void registerCommands() {
        getCommand("help").setExecutor(new HelpCommand(this));
		getCommand("kick").setExecutor(new KickCommand(this));
		getCommand("ban").setExecutor(new BanCommand(this));	
		getCommand("ci").setExecutor(new CiCommand(this));
		getCommand("stuck").setExecutor(new StuckCommand(this));
		getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
		getCommand("spawn").setExecutor(new SpawnCommand(this));
		getCommand("tp").setExecutor(new TpCommand(this));
		getCommand("tph").setExecutor(new TphCommand(this));
        getCommand("time").setExecutor(new TimeCommand(this));
        getCommand("armor").setExecutor(new ArmorCommand(this));		
	}
	
	public void registerRecipes() {
		// Smelt clayblokker til brick.
		FurnaceRecipe clayToBrickFurnaceRecipe = new FurnaceRecipe(new ItemStack(Material.BRICK, 1), Material.CLAY);
	    getServer().addRecipe(clayToBrickFurnaceRecipe);
	    
	    // Lag clayballer av clayblokk.
	    ShapedRecipe clayRecipe = new ShapedRecipe(new ItemStack(Material.CLAY_BALL, 4));
	    clayRecipe.shape(new String[] { "c" });
	    clayRecipe.setIngredient('c', Material.CLAY);
	    getServer().addRecipe(clayRecipe);
	        
	    // Lag snøballer av snøblokk.
	    ShapedRecipe snowRecipe = new ShapedRecipe(new ItemStack(Material.SNOW_BALL, 4));
	    clayRecipe.shape(new String[] { "s" });
	    clayRecipe.setIngredient('s', Material.SNOW_BLOCK);
	    getServer().addRecipe(snowRecipe);
	}
	
	public void sqlConnection() {
        Connection conn = sqlConnector.createConnection();

		if (conn == null) {
			pm.disablePlugin(this);
			
			log(Level.SEVERE, "Kunne ikke opprette forbindelse til mysql, disabler plugin.");
			
			return;
		} else {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				log(Level.SEVERE, "Feil under lukking av mysql tilkobling.");
			}
		}
		
		sqlHandler.initialize();
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
    
	public MySQLHandler getSqlHandler() {
		return sqlHandler;
	}
	
	public MySQLConnector getMySQLConnector() {
		return sqlConnector;
	}
	
	public MySQLObject getMySQLObject() {
		return sqlObject;
	}
}