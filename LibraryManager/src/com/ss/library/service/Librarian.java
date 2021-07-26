/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.util.List;

import com.ss.library.dao.BookCopiesDAO;
import com.ss.library.dao.BookDAO;
import com.ss.library.dao.LibraryBranchDAO;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.LibraryBranch;

/**
 * @author Robert Haas
 *
 */
public class Librarian<T> {
	Util util = new Util();

	public List<LibraryBranch> readLibraryBranch() { // Returns a list of all the library branches in the database
		Connection conn = null;
		List<LibraryBranch> list = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			list = lbdao.readAllLibraryBranches();
		} catch (Exception e) {
			System.out.println("Could not read from the Library Branch table");
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
	
	public void updateLibraryBranch(LibraryBranch lib) { // updates the library branch
		Connection conn = null;
		try {
			conn = util.getConnection();
			LibraryBranchDAO lbdao = new LibraryBranchDAO(conn);
			lbdao.updateLibraryBranch(lib);
			conn.commit();
			System.out.println("Branch has been successfully updated!");
		} catch (Exception e) {
			System.out.println("Could not update the Library Branch table");
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
	}
	
	public List<Book> readBook() { // Returns a list of all the books in the database
		Connection conn = null;
		List<Book> list = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			list = bdao.readAllBooks();
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
	
	public List<BookCopies> readCopiesList(Book b, LibraryBranch l) { // Returns a list of bookCopies based on bookId and branchId
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			List<BookCopies> list = bcdao.readBookCopiesById(b, l);
			return list;
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
	
	public void updateBookCopies(BookCopies bookC) { // Updates the book copies
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.updateBookCopies(bookC);
			conn.commit();
			System.out.println("Book Copies has been successfully updated!");
		} catch (Exception e) {
			System.out.println("Could not update the Book Copies table");
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
	}
	
	public void addBookCopies(BookCopies bookC) { // Adds to the book copies table
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.addBookCopies(bookC);
			conn.commit();
			System.out.println("Book Copies has been successfully updated!");
		} catch (Exception e) {
			System.out.println("Could not update the Book Copies table");
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
	}
}
