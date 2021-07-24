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

/**
 * @author Robert Haas
 *
 */
public class AuthorDAO extends BaseDAO<Author> {
	public AuthorDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_author values (?, ?)", new Object[] {author.getAuthorID(), author.getAuthorName()});
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("update library.tbl_author set authorName = ? where authorId = ?", 
				new Object[] {author.getAuthorName(), author.getAuthorID()});
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_author where authorId = ?", new Object[] {author.getAuthorID()});
	}
	
	public List<Author> readAllAuthors() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_author", null);
	}
	
	public List<Author> readAuthorsByID(Author author) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_author where authorId = ?", new Object[] {author.getAuthorID()});
	}
	
	public List<Author> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List <Author> authors = new ArrayList<>();
		
		while(rs.next()) {
			Author a = new Author();
			a.setAuthorID(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}
}
