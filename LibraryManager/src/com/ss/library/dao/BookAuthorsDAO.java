/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookAuthors;

/**
 * @author Robert Haas
 *
 */
public class BookAuthorsDAO extends BaseDAO<BookAuthors> {
	public BookAuthorsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addBookAuthors(BookAuthors auth) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_book_authors values (?, ?)", new Object[] {auth.getBook().getBookID(), auth.getAuthor().getAuthorID()});
	}

	public void deleteBookAuthors(BookAuthors auth) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_book_authors where bookId = ? and authorId = ?", new Object[] {auth.getBook().getBookID(), 
				auth.getAuthor().getAuthorID()});
	}
	
	public List<BookAuthors> readAllBookAuthors() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_authors natural join library.tbl_book natural join library.tbl_author", null);
	}
	
	public List<BookAuthors> readAllAuthorsByBookId(int id) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_authors natural join library.tbl_book natural join library.tbl_author"
				+ " where bookId = ?", new Object[] {id});
	}
	
	public List<BookAuthors> readAllBooksByAuthorId(int id) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_authors natural join library.tbl_book natural join library.tbl_author"
				+ " where authorId = ?", new Object[] {id});
	}
	
	@Override
	public List<BookAuthors> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookAuthors> copy = new ArrayList<>();

		while(rs.next()) {
			BookAuthors ba = new BookAuthors();
			Book book = new Book();
			Author author = new Author();
			
			book.setBookID(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubID(rs.getInt("pubId"));
			ba.setBook(book);
			
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			ba.setAuthor(author);
			
			
			copy.add(ba);
		}
		return copy;
	}
}
