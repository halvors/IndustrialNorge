/* 
 * Copyright (C) 2011 halvors <halvors@skymiastudios.com>
 * Copyright (C) 2011 speeddemon92 <speeddemon92@gmail.com>
 * Copyright (C) 2011 adamonline45 <adamonline45@gmail.com>
 * 
 * This file is part of Lupi.
 * 
 * Lupi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Lupi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Lupi.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.halvors.halvors;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
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
        
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
        
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, playerListener, Event.Priority.Normal, this);

        // Register our commands.
        getCommand("help").setExecutor(new HelpCommand(this));
        getCommand("stuck").setExecutor(new StuckCommand(this));
        getCommand("time").setExecutor(new TimeCommand(this));
        
        getCommand("hkick").setExecutor(new KickCommand(this));
        getCommand("hban").setExecutor(new BanCommand(this));
        
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
    
    public static halvors getInstance() {
        return instance;
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