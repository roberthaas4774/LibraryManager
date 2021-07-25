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
import com.ss.library.entity.BookGenres;
import com.ss.library.entity.Genre;

/**
 * @author Robert Haas
 *
 */
public class BookGenresDAO extends BaseDAO<BookGenres> {

	public BookGenresDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBookGenres(BookGenres bookG) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_book_genres values (?, ?)",
				new Object[] { bookG.getGenre().getGenreID(), bookG.getBook().getBookID() });
	}

	public void deleteBookGenres(BookGenres bookG) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_book_genres where genre_id = ? and bookId = ?",
				new Object[] { bookG.getGenre().getGenreID(), bookG.getBook().getBookID() });
	}

	public List<BookGenres> readAllBookGenres() throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_book_genres natural join library.tbl_genres natural join library.tbl_book", null);
	}
	
	public List<BookGenres> readBookGenresByBookId(int id) throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_book_genres natural join library.tbl_genre natural join library.tbl_book"
				+ " where bookId = ?", new Object[] {id});
	}
	
	public List<BookGenres> readBookGenresByGenreId(int id) throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_book_genres natural join library.tbl_genre natural join library.tbl_book"
				+ " where genre_id = ?", new Object[] {id});
	}

	@Override
	public List<BookGenres> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookGenres> bookGenres = new ArrayList<>();

		while (rs.next()) {
			BookGenres bg = new BookGenres();
			Genre genre = new Genre();
			Book book = new Book();
			
			genre.setGenreID(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			bg.setGenre(genre);
			
			book.setBookID(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubID(rs.getInt("pubId"));
			bg.setBook(book);
			
			bookGenres.add(bg);
		}
		return bookGenres;
	}

}
