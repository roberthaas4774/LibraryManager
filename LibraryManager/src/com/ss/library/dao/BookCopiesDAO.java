/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Book;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.LibraryBranch;

/**
 * @author Robert Haas
 *
 */
public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addBookCopies(BookCopies bookCopy) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_book_copies values (?, ?, ?)", new Object[] {bookCopy.getBook().getBookID(), 
				bookCopy.getBranch().getBranchID(), bookCopy.getCopies()});
	}

	public void updateBookCopies(BookCopies bookCopy) throws ClassNotFoundException, SQLException {
		save("update library.tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?", 
				new Object[] {bookCopy.getCopies(), bookCopy.getBook().getBookID(), bookCopy.getBranch().getBranchID()});
	}

	public void deleteBookCopies(BookCopies bookCopy) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_book_copies where bookId = ? and branchId = ?", new Object[] {
				bookCopy.getBook().getBookID(), bookCopy.getBranch().getBranchID()});
	}
	
	public List<BookCopies> readAllBookCopies() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_copies", null);
	}

	public List<BookCopies> readBookCopiesByBranchId(int l) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_copies natural join library.tbl_book natural join library.tbl_library_branch"
				+ " where branchId = ?", new Object[] {l});
	}
	
	public List<BookCopies> readBookCopiesByBookId(int b) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_copies natural join library.tbl_book natural join library.tbl_library_branch"
				+ " where bookId = ?", new Object[] {b});
	}
	
	public List<BookCopies> readBookCopiesById(Book book, LibraryBranch libBranch) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_copies natural join library.tbl_book natural join library.tbl_library_branch"
				+ " where bookId = ? and branchId = ?", new Object[] {book.getBookID(), libBranch.getBranchID()});
	}
	
	@Override
	public List<BookCopies> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookCopies> copy = new ArrayList<>();

		while(rs.next()) {
			BookCopies bc = new BookCopies();
			Book book = new Book();
			LibraryBranch lib = new LibraryBranch();
			
			book.setBookID(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			bc.setBook(book);
			
			lib.setBranchID(rs.getInt("branchId"));
			lib.setBranchName(rs.getString("branchName"));
			lib.setBranchAddress(rs.getString("branchAddress"));
			bc.setBranch(lib);
			
			bc.setCopies(rs.getInt("noOfCopies"));
			copy.add(bc);
		}
		return copy;
	}

}
