/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Robert Haas
 *
 */
public abstract class BaseDAO<T> {
	protected Connection conn = null;
	
	public BaseDAO(Connection conn) {
		this.conn = conn;
	}
	
	public Integer saveReturnPK(String sql, Object[] vals) throws ClassNotFoundException, SQLException { // Adds to the database and returns the primary key
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for(Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys(); 

		while (rs.next()) {
			return rs.getInt(1); 
		}
		return null;
	}
	
	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException { // Sends the query to the database
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int count = 1;
		for(Object o : vals) {
			pstmt.setObject(count, o);
			count++;
		}
		pstmt.executeUpdate();
	}
	
	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException { // Gets a list from the database
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (vals != null) {
			int count = 1;
			for(Object o : vals) {
				pstmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract public List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;

}
