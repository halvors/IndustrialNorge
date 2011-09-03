package org.halvors.halvors.util;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.util.config.Configuration;
import org.halvors.halvors.halvors;

/**
 * Holds the configuration for individual worlds.
 * 
 * @author halvors
 */
public class WorldConfiguration {
//    private final halvors plugin;
    
    private final ConfigurationManager configManager;
    
    private String worldName;
    private Configuration config;
    private File configFile;
    
    /* Configuration data start */
    
    /* Configuration data end */
    
    public WorldConfiguration(halvors plugin, String worldName) {
//        this.plugin = plugin;
        this.configManager = plugin.getConfigurationManager();
        this.worldName = worldName;

        File baseFolder = new File(plugin.getDataFolder(), "worlds/");
        configFile = new File(baseFolder, worldName + ".yml");
        
        configManager.createDefaultConfiguration(configFile, "config_world.yml");
        config = new Configuration(configFile);
        
        load();

        plugin.log(Level.INFO, "Loaded configuration for world '" + worldName + '"');
    }
    
    /**
     * Load the configuration.
     */
    private void load() {
        config.load();
        
        config.save();
    }
    
    public String getWorldName() {
        return worldName;
    }
}
