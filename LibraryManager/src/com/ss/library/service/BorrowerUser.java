/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.util.List;

import com.ss.library.dao.BookCopiesDAO;
import com.ss.library.dao.BookLoansDAO;
import com.ss.library.dao.BorrowerDAO;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.BookLoans;
import com.ss.library.entity.Borrower;
import com.ss.library.entity.LibraryBranch;

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
			List<BookCopies> bookCopies = bcdao.readAvailableCopiesInBranch(id);
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
	
	public List<BookCopies> readCopiesById(Book b, LibraryBranch l) {
		Connection conn = null;
		List<BookCopies> list = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			// pass connections
			List<BookCopies> bookCopies = bcdao.readBookCopiesById(b, l);
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

	public void updateBookCopies(BookCopies bookC) {
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
	
	public List<BookLoans> readBookLoansById(BookLoans bookL) {
		List<BookLoans> list = null;
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			list = bldao.readBookLoanByID(bookL);
			return list;
		} catch (Exception e) {
			System.out.println("Could not read the Book Loans table");
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
	
	public List<BookLoans> readBookLoans() {
		List<BookLoans> list = null;
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			list = bldao.readAllBookLoans();
			return list;
		} catch (Exception e) {
			System.out.println("Could not read the Book Loans table");
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

	public boolean addBookLoans(BookLoans bookL) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.addBookLoan(bookL);
			conn.commit();
			System.out.println("Book Loan has been successfully created!");
			return true;
		} catch (Exception e) {
			System.out.println("Could not add to the Book Loans table");
			try {
				conn.rollback();
			} catch (Exception e2) {
				System.out.println("Could not rollback successfully");
			}

		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("Could not close the connection successfully");
			}
		}
		return false;
	}

	public void updateBookLoans(BookLoans bookL) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.updateBookLoan(bookL);
			conn.commit();
			System.out.println("Book Loan has been successfully updated!");
		} catch (Exception e) {
			System.out.println("Could not update the Book Loans table");
			try {
				conn.rollback();
			} catch (Exception e2) {
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
	
	public List<BookLoans> readLoanedBooks(int bor) {
		List<BookLoans> list = null;
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			list = bldao.readBookLoanByPerson(bor);
			return list;
		} catch (Exception e) {
			System.out.println("Could not read the Book Loans table");
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