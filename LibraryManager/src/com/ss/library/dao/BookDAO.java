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

/**
 * @author Robert Haas
 *
 */
public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public Integer addBook(Book book) throws ClassNotFoundException, SQLException{
		return saveReturnPK("insert into library.tbl_book (title, pubId) values (?, ?)", new Object[] {book.getTitle(), book.getPubID()});
	}
	
	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("update library.tbl_book set title = ?, pubId = ? where bookId = ?", 
				new Object[] {book.getTitle(), book.getPubID(), book.getBookID()});
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_book where bookId = ?", new Object[] {book.getBookID()});
	}
	
	public List<Book> readAllBooks() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book", null);
	}
	
	public List<Book> readAllBooksAndMore() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_authors natural join library.tbl_author natural join "
				+ "library.tbl_book natural join library.tbl_book_genres natural join library.tbl_genre "
				+ "natural join library.tbl_publisher;", null);
	}
	
	public List<Book> readBooksByID(Book book) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book where bookId = ?", new Object[] {book.getBookID()});
	}
	
//	public List<Book> readBookLoanByPerson(int cardNo) throws ClassNotFoundException, SQLException{
//		return read("select  from library.tbl_book_loans natural join library.tbl_book where cardNo = ?", new Object[] {cardNo});
//	}
	
	public List<Book> readBookByPubId(int id) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book where pubId = ?", new Object[] {id});
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
