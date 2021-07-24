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
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.LibraryBranch;

/**
 * @author Robert Haas
 *
 */
public class BookAuthorsDAO extends BaseDAO<BookAuthors> {
	public BookAuthorsDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public List<BookAuthors> readAllBookAuthors() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_authors", null);
	}

//	@Override
//	public List<BookAuthors> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
//		List<BookAuthors> bookAuthors = new ArrayList<>();
//
//		while(rs.next()) {
//			Book book = new Book();
//			Author author = new Author();
//			BookAuthors ba = new BookAuthors();
//			
//			book.setBookID(rs.getInt("bookId"));
////			book.setTitle(rs.getString("title"));
//			ba.setBook(book);
//			
//			author.setAuthorID(rs.getInt("authorId"));
////			author.setAuthorName(rs.getString("authorName"));
//			ba.setAuthor(author);
//			
//			bookAuthors.add(ba);
//		}
//		return bookAuthors;
//	}
	
	@Override
	public List<BookAuthors> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookAuthors> copy = new ArrayList<>();

		while(rs.next()) {
			BookAuthors ba = new BookAuthors();
			Book book = new Book();
			Author author = new Author();
			
			book.setBookID(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			ba.setBook(book);
			
			author.setAuthorID(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			ba.setAuthor(author);
			
			
			copy.add(ba);
		}
		return copy;
	}
}
