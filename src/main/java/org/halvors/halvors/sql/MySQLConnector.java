package org.halvors.halvors.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.halvors.halvors.halvors;
import org.halvors.halvors.util.ConfigurationManager;

public class MySQLConnector {
//	private final halvors plugin;
	private final ConfigurationManager configManager;
	
	public ResultSet result;
	
	public MySQLConnector(halvors plugin) {
//		this.plugin = plugin;
		this.configManager = plugin.getConfigurationManager();
	}
		
	public synchronized Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + configManager.dbhost + ":" + configManager.dbport + "/" + configManager.dbname + "", configManager.dbuser, configManager.dbpass);
			
			Logger.getLogger("Minecraft").log(Level.INFO, "[Insane] Koblet til mysql databasen");
			return conn;
		} catch (SQLException e) {
			Logger.getLogger("Minecraft").log(Level.SEVERE, "[Insane] Kunne ikke koble til databasen", e);
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