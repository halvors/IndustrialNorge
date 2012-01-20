package no.industrialnorge.industrialnorge.util;

import java.util.HashMap;
import java.util.Map.Entry;

import no.industrialnorge.industrialnorge.IndustrialNorge;

import org.bukkit.configuration.ConfigurationSection;

public class Configuration {
//	private final IndustrialNorge plugin;	
	private final ConfigurationSection config;
	private final HashMap<String, Object> def = new HashMap<String, Object>();
	
	public boolean enableMotd;
	
	public Configuration(IndustrialNorge plugin) {
//		this.plugin = plugin;
		this.config = plugin.getConfig();
		
		def.put("enableMotd", true);
		
		for (Entry<String, Object> e : def.entrySet()) {
			if (!config.contains(e.getKey())) {
				config.set(e.getKey(), e.getValue());
			}
		}
	
		plugin.saveConfig();

		enableMotd = config.getBoolean("enableMotd");
	}
}