/**********************************************************************************************
 * @author Jitender Chahar
 * 
 * Utility class for database
 *
 **********************************************************************************************/
package com.jsc.javageeks.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseUtils {

	public static Properties properties = new Properties();
	public static InputStream inputStream = null;

	public static Connection connection;
	public static String driver;
	public static String connectionURL;
	public static String user;
	public static String password;

	/**
	 * private constructor reading the properties of driver, connectionURL,
	 * user, password from property file config.properties
	 * 
	 */
	public DatabaseUtils() {
		try {
			inputStream = getClass().getResourceAsStream("/config.properties");
			// load a properties file
			properties.load(inputStream);
			// get the property value
			driver = properties.getProperty("driver");
			connectionURL = properties.getProperty("connectionURL");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		}
	}

	/**
	 * Method to return the connection
	 * 
	 * @return Connection
	 */
	public static Connection createConnection() {
		try {
			// Loading the driver
			Class.forName(driver);
			// Creating a connection
			connection = DriverManager.getConnection(connectionURL, user, password);
		} catch (ClassNotFoundException exception) {
			System.out.println("Driver not found");
		} catch (SQLException exception) {
			System.out.println("Connection exception" + exception);
		}

		return connection;
	}

	/**
	 * Method to close the connection
	 * 
	 * @param connection
	 */
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * Method to close the PreparedStatement
	 * 
	 * @param preparedStatement
	 */
	public static void closeStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		}
	}

	/**
	 * to get a result set of a query
	 * 
	 * @param connection
	 *            connection object
	 * @param query
	 *            custom query
	 * @return a result set of custom query
	 * @throws SQLException
	 *             throws an exception if an error occurs
	 */
	public static ResultSet getResultSet(Connection connection, String query) throws SQLException {
		ResultSet rs;
		PreparedStatement st = connection.prepareStatement(query);
		rs = st.executeQuery();
		return rs;
	}

	/**
	 * to run an update query such as update, delete
	 * 
	 * @param query
	 *            custom query
	 * @throws SQLException
	 *             throws an exception if an error occurs
	 */
	public static int runQuery(Connection con, String query) throws SQLException {
		PreparedStatement st = con.prepareStatement(query);
		return st.executeUpdate();
	}
}