package org.halvors.halvors;

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
import org.halvors.halvors.command.HelpCommand;
import org.halvors.halvors.command.KickCommand;
import org.halvors.halvors.command.StuckCommand;
import org.halvors.halvors.command.TimeCommand;
import org.halvors.halvors.listener.BlockListener;
import org.halvors.halvors.listener.EntityListener;
import org.halvors.halvors.listener.PlayerListener;
import org.halvors.halvors.util.ConfigurationManager;

public class halvors extends JavaPlugin {
    private final Logger logger = Logger.getLogger("Minecraft");
    
    private PluginManager pm;
    private PluginDescriptionFile desc;
    
    private final ConfigurationManager configManager;
    
    private final BlockListener blockListener;
    private final EntityListener entityListener;
    private final PlayerListener playerListener;
    
    private static halvors instance;
    
    public halvors() {
    	halvors.instance = this;
        
        this.configManager = new ConfigurationManager(this);
        
        this.blockListener = new BlockListener(this);
        this.entityListener = new EntityListener(this);
        this.playerListener = new PlayerListener(this);
    }
    
    @Override
    public void onEnable() {
        pm = getServer().getPluginManager();
        desc = getDescription();
        
        // Load configuration.
        configManager.load();
        
        // Register our events.
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
        
        pm.registerEvent(Event.Type.CREATURE_SPAWN, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
        
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);

        // Register our commands.
        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("stuck").setExecutor(new StuckCommand(this));
        getCommand("time").setExecutor(new TimeCommand(this));
        getCommand("armor").setExecutor(new ArmorCommand(this));
        
        getCommand("hkick").setExecutor(new KickCommand(this));
        getCommand("hban").setExecutor(new BanCommand(this));
        
        // Register our recipes.
        FurnaceRecipe clayToBrickFurnaceRecipe = new FurnaceRecipe(new ItemStack(Material.BRICK, 1), Material.CLAY);
        getServer().addRecipe(clayToBrickFurnaceRecipe);

        ShapedRecipe clayRecipe = new ShapedRecipe(new ItemStack(Material.CLAY_BALL, 4));
        clayRecipe.shape(new String[] { "c" });
        clayRecipe.setIngredient('c', Material.CLAY);
        getServer().addRecipe(clayRecipe);
        
        log(Level.INFO, "version " + getVersion() + " is enabled!");
    }
    
    @Override
    public void onDisable() {
        // Save configuration.
        configManager.unload();
        
        log(Level.INFO, "version " + getVersion() + " is disabled!");
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