package org.halvors.halvors.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * <b>Purpose:</b>Realizes a connection pool for all JDBC connections.<br>
 * <b>Description:</b>http://java.sun.com/developer/onlineTraining/Programming/
 * JDCBook/ conpool.html<br>
 * <b>Copyright:</b>Licensed under the Apache License, Version 2.0.
 * http://www.apache.org/licenses/LICENSE-2.0<br>
 * <b>Company:</b>SIMPL<br>
 * 
 * @author schneimi
 * @version $Id$<br>
 * @link http://code.google.com/p/simpl09/
 */
public class ConnectionService {
	private Vector<JDCConnection> connections;
	private String url, user, password;
	final private long timeout = 60000;
	private ConnectionReaper reaper;
	final private int poolsize = 10;

	public ConnectionService(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		this.connections = new Vector<JDCConnection>(poolsize);
		this.reaper = new ConnectionReaper(this);
		this.reaper.start();
	}

	public synchronized void reapConnections() {
		long stale = System.currentTimeMillis() - timeout;
		Enumeration<JDCConnection> connlist = this.connections.elements();

		while ((connlist != null) && (connlist.hasMoreElements())) {
			JDCConnection conn = connlist.nextElement();

			if ((conn.inUse()) && (stale > conn.getLastUse())
					&& (!conn.validate())) {
				removeConnection(conn);
			}
		}
	}

	public synchronized void closeConnections() {
		Enumeration<JDCConnection> connlist = this.connections.elements();

		while ((connlist != null) && (connlist.hasMoreElements())) {
			JDCConnection conn = connlist.nextElement();
			removeConnection(conn);
		}
	}

	private synchronized void removeConnection(JDCConnection conn) {
		this.connections.removeElement(conn);
	}

	public synchronized Connection getConnection() throws SQLException {
		JDCConnection c;

		for (int i = 0; i < this.connections.size(); i++) {
			c = this.connections.elementAt(i);
			if (c.lease()) {
//				Insane.log.log(Level.INFO, "Connection leased.");
				return c;
			}
		}

		Connection conn = DriverManager.getConnection(url, user, password);
		c = new JDCConnection(conn, this);
		c.lease();
		this.connections.addElement(c);
		
//		Insane.log.log(Level.INFO, this.connections.size() + " SQL connections made.");

		return c.getConnection();
	}

	public synchronized void returnConnection(JDCConnection conn) {
		conn.expireLease();
	}
}

class ConnectionReaper extends Thread {
	private ConnectionService pool;
	private final long delay = 300000;

	ConnectionReaper(ConnectionService pool) {
		this.pool = pool;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
			this.pool.reapConnections();
		}
	}
}
