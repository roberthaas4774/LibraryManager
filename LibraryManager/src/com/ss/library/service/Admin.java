/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.dao.BookDAO;
import com.ss.library.dao.LibraryBranchDAO;

/**
 * @author Robert Haas
 *
 */
public class Admin {
	
	Util util = new Util();
	
	public String addLibraryBranch() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			// pass connections
			conn.commit();
			return "Library Branch added successfully";
		} catch (Exception e) {
			System.out.println("Could not add Library Branch");
			try {
				conn.rollback();
			} catch (Exception e1) {
				System.out.println("Could not rollback successfully");
			}
			return "Library Branch was not added successfully";
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Could not close the connection successfully");
			}
		}
	}
}
