package org.halvors.halvors.sql;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.halvors.halvors.halvors;
import org.halvors.halvors.handlers.MySQLHandler;

public class MySQLObject {
	private final MySQLHandler sqlHandler;
	
	public MySQLObject(halvors plugin) {
		this.sqlHandler = plugin.getSqlHandler();
	}
	
	public void replace(String name, int type, Object object) {
		try {
			Connection conn = sqlHandler.getConnection();
			
			PreparedStatement query = conn.prepareStatement("REPLACE INTO `objects` (`name`, `type`, `object`) VALUES(?, ?, ?)");
			
			query.setString(1, name);
			query.setInt(2, type);
			query.setObject(3, object);
			
			query.executeUpdate();
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String name, int type, Object object) {
		try {
			Connection conn = this.sqlHandler.getConnection();
			
			PreparedStatement query = conn.prepareStatement("INSERT INTO `objects` (`name`, `type`, `object`) VALUES(?, ?, ?)");
			
			query.setString(1, name);
			query.setInt(2, type);
			query.setObject(3, object);
			
			query.executeUpdate();
			
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(String name, int type) {
		
	}
	
	public Object get(String name, int type) {
		
		Object object = null;
		
		try {
			Connection conn = this.sqlHandler.getConnection();
			
			PreparedStatement query = conn.prepareStatement("SELECT object FROM `objects` WHERE name = ? AND type = ?");
			
			query.setString(1, name);
			query.setInt(2, type);
			
			ResultSet rs = query.executeQuery();
			
			rs.next();
			InputStream is = rs.getBlob(1).getBinaryStream();
		    ObjectInputStream oip;
			oip = new ObjectInputStream(is);
		    object = oip.readObject();
		    oip.close();
		    is.close();
		    
		    query.close();
		    
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return object;
	}
}