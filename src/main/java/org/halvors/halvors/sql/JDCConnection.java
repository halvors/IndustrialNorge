package org.halvors.halvors.sql;


import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * <b>Purpose:</b>Wrapper for JDBCConnection.<br>
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
public class JDCConnection implements Connection {
	private ConnectionService pool;
	private Connection conn;
	private boolean inuse;
	private long timestamp;

	public void abort(Executor e) {}
 	public int getNetworkTimeout() { return 0; }
 	public String getSchema() { return ""; }
 	public void setNetworkTimeout(Executor e,int i) {}
 	public void setSchema(String s) {}
 	
	public JDCConnection(Connection conn, ConnectionService pool) {
		this.conn = conn;
		this.pool = pool;
		this.inuse = false;
		this.timestamp = 0;
	}

	public synchronized boolean lease() {
		if (inuse) {
			return false;
		} else {
			inuse = true;
			timestamp = System.currentTimeMillis();
			return true;
		}
	}

	public boolean validate() {
		try {
			conn.getMetaData();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean inUse() {
		return inuse;
	}

	public long getLastUse() {
		return timestamp;
	}

	public void close() throws SQLException {
		pool.returnConnection(this);
	}

	protected void expireLease() {
		inuse = false;
	}

	protected Connection getConnection() {
		return conn;
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		return conn.prepareCall(sql);
	}

	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	public String nativeSQL(String sql) throws SQLException {
		return conn.nativeSQL(sql);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conn.setAutoCommit(autoCommit);
	}

	public boolean getAutoCommit() throws SQLException {
		return conn.getAutoCommit();
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public void rollback() throws SQLException {
		conn.rollback();
	}

	public boolean isClosed() throws SQLException {
		return conn.isClosed();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return conn.getMetaData();
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		conn.setReadOnly(readOnly);
	}

	public boolean isReadOnly() throws SQLException {
		return conn.isReadOnly();
	}

	public void setCatalog(String catalog) throws SQLException {
		conn.setCatalog(catalog);
	}

	public String getCatalog() throws SQLException {
		return conn.getCatalog();
	}

	public void setTransactionIsolation(int level) throws SQLException {
		conn.setTransactionIsolation(level);
	}

	public int getTransactionIsolation() throws SQLException {
		return conn.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		return conn.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		conn.clearWarnings();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createArrayOf(java.lang.String,
	 * java.lang.Object[])
	 */
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return conn.createArrayOf(typeName, elements);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createBlob()
	 */
	public Blob createBlob() throws SQLException {
		return createBlob();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createClob()
	 */
	public Clob createClob() throws SQLException {
		return conn.createClob();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createNClob()
	 */
	public NClob createNClob() throws SQLException {
		return conn.createNClob();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createSQLXML()
	 */
	public SQLXML createSQLXML() throws SQLException {
		return conn.createSQLXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency,
				resultSetHoldability);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#createStruct(java.lang.String,
	 * java.lang.Object[])
	 */
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return conn.createStruct(typeName, attributes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getClientInfo()
	 */
	public Properties getClientInfo() throws SQLException {
		return conn.getClientInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	public String getClientInfo(String name) throws SQLException {
		return conn.getClientInfo(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getHoldability()
	 */
	public int getHoldability() throws SQLException {
		return conn.getHoldability();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#getTypeMap()
	 */
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#isValid(int)
	 */
	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String,
	 * java.lang.String[])
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int,
	 * int)
	 */
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	public void rollback(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setClientInfo(java.lang.String,
	 * java.lang.String)
	 */
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setHoldability(int)
	 */
	public void setHoldability(int holdability) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setSavepoint()
	 */
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	public Savepoint setSavepoint(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
