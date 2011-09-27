package org.halvors.halvors.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import org.halvors.halvors.halvors;
import org.halvors.halvors.util.ConfigurationManager;

public class MySQLConnector {
	private final halvors plugin;
	private final ConfigurationManager configManager;
	
	public MySQLConnector(halvors plugin) {
		this.plugin = plugin;
		this.configManager = plugin.getConfigurationManager();
	}
		
	public synchronized Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + configManager.dbhost + ":" + configManager.dbport + "/" + configManager.dbname + "", configManager.dbuser, configManager.dbpass);
			plugin.log(Level.INFO, "Koblet til mysql databasen");
			
			return conn;
		} catch (SQLException e) {
			plugin.log(Level.SEVERE, "Kunne ikke koble til databasen");
			
			return null;
		}
	}
	
	public Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection ret = DriverManager.getConnection("jdbc:mysql://" + configManager.dbhost + ":" + configManager.dbport + "/" + configManager.dbname + "", configManager.dbuser, configManager.dbpass);
			
			return ret;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
	}
}