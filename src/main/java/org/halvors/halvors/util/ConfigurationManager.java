package org.halvors.halvors.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.World;
import org.bukkit.util.config.Configuration;
import org.halvors.halvors.halvors;

/**
 * Represents the global configuration and also delegates configuration
 * for individual worlds.
 * 
 * @author halvors
 */
public class ConfigurationManager {
    private final halvors plugin;
    private final HashMap<String, WorldConfiguration> worlds;
    
    private static ConfigurationManager instance;
    
    public ConfigurationManager(halvors plugin) {
        this.plugin = plugin;
        this.worlds = new HashMap<String, WorldConfiguration>();
        
        ConfigurationManager.instance = this;
    }
    
    public static ConfigurationManager getInstance() {
        return instance;
    }
    
    /**
     * Load the configuration.
     */
    public void load() {
        // Create the default configuration file
        createDefaultConfiguration(new File(plugin.getDataFolder(), "config.yml"), "config.yml");
            
        Configuration config = plugin.getConfiguration();
        config.load();

        // Load configurations for each world
        for (World world : plugin.getServer().getWorlds()) {
            get(world);
        }
        
        config.save();
    }

    /**
     * Unload the configuration.
     */
    public void unload() {
        worlds.clear();
    }
    
    /**
     * Reload the configuration.
     */
    public void reload() {
        unload();
        load();
    }

    /**
     * Get the configuration for a world.
     *
     * @param world
     * @return
     */
    public WorldConfiguration get(World world) {
        String worldName = world.getName();
        WorldConfiguration config = worlds.get(worldName);
            
        if (config == null) {
            config = new WorldConfiguration(plugin, worldName);
            worlds.put(worldName, config);
        }
        
        return config;
    }
    
    /**
    * Create a default configuration file from the .jar.
    *
    * @param actual
    * @param defaultName
    */
    public void createDefaultConfiguration(File actual, String defaultName) {
        // Make parent directories
        File parent = actual.getParentFile();
        
        if (!parent.exists()) {
            parent.mkdirs();
        }
        
        if (actual.exists()) {
            return;
        }
        
        InputStream input = ConfigurationManager.class.getResourceAsStream("/" + defaultName);
        
        if (input != null) {
            FileOutputStream output = null;

            try {
                output = new FileOutputStream(actual);
                byte[] buf = new byte[8192];
                int length = 0;
                
                while ((length = input.read(buf)) > 0) {
                    output.write(buf, 0, length);
                }
                
                plugin.log(Level.INFO, "Default configuration file written: " + actual.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) {
                        input.close();
                    }
                } catch (IOException e) {
                    
                }

                try {
                    if (output != null) {
                        output.close();
                    }
                } catch (IOException e) {
                    
                }
            }
        }
    }
}
