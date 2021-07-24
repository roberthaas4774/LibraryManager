/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.util.List;

import com.ss.library.dao.BookCopiesDAO;
import com.ss.library.dao.BorrowerDAO;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.Borrower;

/**
 * @author Robert Haas
 *
 */
public class BorrowerUser<T> {
	Util util = new Util();

	public List<Borrower> readBorrowerId(int id) {
		Connection conn = null;
		List<Borrower> list = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			// pass connections
			list = bdao.readBorrowersByID(id);
		} catch (Exception e) {
			System.out.println("Could not read from the Book table");
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
		return list;
	}
	
	public List<BookCopies> readAvailableBooks(int id) {
		Connection conn = null;
		List<BookCopies> list = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			// pass connections
			List<BookCopies> bookCopies = bcdao.readBookCopiesByBranchId(id);
			return bookCopies;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Copies table");
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