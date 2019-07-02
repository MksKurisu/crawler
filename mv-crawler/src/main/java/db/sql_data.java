package db;

import java.sql.*;

public class sql_data {
	String sDBDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://182.149.196.16/xia";

	String user = "root";
	String password = "x931118x";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	public sql_data() {
		try {
			Class.forName(sDBDriver);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("sql_data(): " + e.getMessage());
		}
	}

	public void executeInsert(String sql) {
		try {// conn = DriverManager.getConnection(url,user,password);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			System.err.println("sql_data.executeUpdate:" + ex.getMessage());
		}
	}

	public ResultSet executeQuery(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println("sql_data.executeQuery:" + ex.getMessage());
		}
		return rs;
	}

	public void executeUpdate(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			System.err.println("aq.executeQuery: " + ex.getMessage());
		}
	}

	public void executeDelete(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			System.err.println("sql_data.executeDelete:" + ex.getMessage());
		}
	}

	public void closeStmt() {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
