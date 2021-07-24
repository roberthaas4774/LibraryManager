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
import com.ss.library.entity.BookLoans;
import com.ss.library.entity.LibraryBranch;
import com.ss.library.entity.Publisher;

/**
 * @author Robert Haas
 *
 */
public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book", null);
	}
	
	public List<Book> readBooksByID(Book book) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book where bookId = ?", new Object[] {book.getBookID()});
	}
	
	public List<Book> readBookLoanByPerson(int cardNo) throws ClassNotFoundException, SQLException{
		return read("select  from library.tbl_book_loans natural join library.tbl_book where cardNo = ?", new Object[] {cardNo});
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Book> book = new ArrayList<>();

		while(rs.next()) {
			Book b = new Book();
			
			b.setBookID(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setPubID(rs.getInt("pubId"));
			book.add(b);
		}
		return book;
	}

}
