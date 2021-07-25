/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Genre;

/**
 * @author Robert Haas
 *
 */
public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_genre values (?, ?)", new Object[] { genre.getGenreID(), genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("update library.tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreID() });
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_genre where genre_id = ?", new Object[] { genre.getGenreID() });
	}

	public List<Genre> readAllGenres() throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_genre", null);
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Genre g = new Genre();
			g.setGenreID(rs.getInt("genre_id"));
			g.setGenreName(rs.getString("genre_name"));
			genres.add(g);
		}
		return genres;
	}

}
