/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.dao.BookAuthorsDAO;
import com.ss.library.dao.BookCopiesDAO;
import com.ss.library.dao.BookDAO;
import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.BookCopies;

/**
 * @author Robert Haas
 *
 */
public class Admin<T> {
	
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
	
	public List<BookAuthors> readBookAuthors() {
		Connection conn = null;
		List<BookAuthors> list = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			// pass connections
			List<BookAuthors> bookAuthors = badao.readAllBookAuthors();
			return bookAuthors;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Authors table");
			try {
				conn.rollback();
			} catch (Exception e1) {
				System.out.println("Could not rollback successfully");
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Could not close the connection successfully");
			}
		}
		return null;
	}
	
	public List<BookCopies> readBookCopies() {
		Connection conn = null;
		List<BookCopies> list = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO badao = new BookCopiesDAO(conn);
			// pass connections
			List<BookCopies> bookAuthors = badao.readAllBookCopies();
			return bookAuthors;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Authors table");
			try {
				conn.rollback();
			} catch (Exception e1) {
				System.out.println("Could not rollback successfully");
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Could not close the connection successfully");
			}
		}
		return null;
	}
}
