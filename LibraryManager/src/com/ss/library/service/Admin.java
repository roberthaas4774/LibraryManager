/**
 * 
 */
package com.ss.library.service;

import java.sql.Connection;
import java.util.List;

import com.ss.library.dao.AuthorDAO;
import com.ss.library.dao.BookAuthorsDAO;
import com.ss.library.dao.BookDAO;
import com.ss.library.dao.BookGenresDAO;
import com.ss.library.dao.GenreDAO;
import com.ss.library.dao.PublisherDAO;
import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.BookGenres;
import com.ss.library.entity.Genre;
import com.ss.library.entity.Publisher;

/**
 * @author Robert Haas
 *
 */
public class Admin<T> {
	
	Util util = new Util();
	
	public void addAuthor(Author author) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			
			conn.commit();
			System.out.println("Successfully added the author");
		} catch (Exception e) {
			System.out.println("Could not add the author");
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
	
	public int addBook(Book book) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			int bookPK = bdao.addBook(book);
			conn.commit();
			System.out.println("Book added successfully");
			return bookPK;
		} catch (Exception e) {
			System.out.println("Could not add Book");
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
		return 0;
	}
	
	public void addGenre(Genre genre) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			
			gdao.addGenre(genre);
			conn.commit();
			System.out.println("Genre added successfully");
		} catch (Exception e) {
			System.out.println("Could not add Genre");
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
	
	public void addPublisher(Publisher pub) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			
			pdao.addPublisher(pub);
			conn.commit();
			System.out.println("Publisher added successfully");
		} catch (Exception e) {
			System.out.println("Could not add Publisher");
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
	
	public void addBookAuthor(BookAuthors bookAuthor) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			
			badao.addBookAuthors(bookAuthor);
			conn.commit();
			System.out.println("Book Author added successfully");
		} catch (Exception e) {
			System.out.println("Could not add Book Author");
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
	
	public void addBookGenre(BookGenres genre) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenresDAO bgdao = new BookGenresDAO(conn);
			
			bgdao.addBookGenres(genre);
			conn.commit();
			System.out.println("Book Genre added successfully");
		} catch (Exception e) {
			System.out.println("Could not add Book Genre");
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
	
	public void updateBook(Book book) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			bdao.updateBook(book);
			conn.commit();
			System.out.println("Book updated successfully");
		} catch (Exception e) {
			System.out.println("Could not update Book");
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
	
	public void updateGenre(Genre genre) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			
			gdao.updateGenre(genre);
			conn.commit();
			System.out.println("Genre updated successfully");
		} catch (Exception e) {
			System.out.println("Could not update Genre");
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
	
	public void updatePublisher(Publisher pub) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			
			pdao.updatePublisher(pub);
			conn.commit();
			System.out.println("Publisher updated successfully");
		} catch (Exception e) {
			System.out.println("Could not update Publisher");
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
	
	public void updateAuthor(Author author) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.updateAuthor(author);
			
			conn.commit();
			System.out.println("Successfully updated the author");
		} catch (Exception e) {
			System.out.println("Could not update the author");
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
	
	public List<Author> readAuthors() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			// pass connections
			List<Author> authors = adao.readAllAuthors();
			return authors;
		} catch (Exception e) {
			System.out.println("Could not read from the Authors table");
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
	
	public List<Genre> readGenres() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			// pass connections
			List<Genre> genres = gdao.readAllGenres();
			return genres;
		} catch (Exception e) {
			System.out.println("Could not read from the Genre table");
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
	
	public List<Publisher> readPubs() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			// pass connections
			List<Publisher> pubs = pdao.readAllPublishers();
			return pubs;
		} catch (Exception e) {
			System.out.println("Could not read from the Publisher table");
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
	
	public List<Book> readBooks() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			// pass connections
			List<Book> book = bdao.readAllBooks();
			return book;
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
		return null;
	}
	
	public List<BookAuthors> readBookAuthorsByBookId(int i) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			// pass connections
			List<BookAuthors> bookAuthor = badao.readAllAuthorsByBookId(i);
			return bookAuthor;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Authors table");
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
	
	public List<BookAuthors> readBookAuthorsByAuthorId(int i) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			// pass connections
			List<BookAuthors> bookAuthor = badao.readAllBooksByAuthorId(i);
			return bookAuthor;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Authors table");
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
	
	public List<BookGenres> readBookGenresByBookId(int i) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenresDAO bgdao = new BookGenresDAO(conn);
			// pass connections
			List<BookGenres> bookGenre = bgdao.readBookGenresByBookId(i);
			return bookGenre;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Genres table");
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
	
	public List<BookGenres> readBookGenresByGenreId(int i) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenresDAO bgdao = new BookGenresDAO(conn);
			// pass connections
			List<BookGenres> bookGenre = bgdao.readBookGenresByGenreId(i);
			return bookGenre;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Genres table");
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
	
	public List<Book> readAllBooksInLibrary() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			// pass connections
			List<Book> book = bdao.readAllBooksAndMore();
			return book;
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
		return null;
	}
	
	public List<Book> readBooksByPubId(int id) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			// pass connections
			List<Book> book = bdao.readBookByPubId(id);
			return book;
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
		return null;
	}
	
	public List<BookAuthors> readAllBooks() {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO bdao = new BookAuthorsDAO(conn);
			// pass connections
			List<BookAuthors> book = bdao.readAllBookAuthors();
			return book;
		} catch (Exception e) {
			System.out.println("Could not read from the Book Authors table");
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
	
	public List<Publisher> readPubById(int id) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			// pass connections
			List<Publisher> pub = pdao.readAllPublishersById(id);
			return pub;
		} catch (Exception e) {
			System.out.println("Could not read from the Publisher table");
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
	
	public void deleteBookAuthor(BookAuthors bookAuthor) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookAuthorsDAO badao = new BookAuthorsDAO(conn);
			
			badao.deleteBookAuthors(bookAuthor);
			conn.commit();
			System.out.println("Book Author deleted successfully");
		} catch (Exception e) {
			System.out.println("Could not delete Book Author");
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
	
	public void deleteBookGenre(BookGenres genre) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookGenresDAO bgdao = new BookGenresDAO(conn);
			
			bgdao.deleteBookGenres(genre);
			conn.commit();
			System.out.println("Book Genre deleted successfully");
		} catch (Exception e) {
			System.out.println("Could not delete Book Genre");
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
	
	public void deletePublisher(Publisher pub) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			
			pdao.deletePublisher(pub);
			conn.commit();
			System.out.println("Publisher deleted successfully");
		} catch (Exception e) {
			System.out.println("Could not delete Publisher");
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
	
	public void deleteBook(Book book) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			
			bdao.deleteBook(book);
			conn.commit();
			System.out.println("Book deleted successfully");
		} catch (Exception e) {
			System.out.println("Could not delete Book");
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
	
	public void deleteAuthor(Author author) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			
			conn.commit();
			System.out.println("Successfully deleted the author");
		} catch (Exception e) {
			System.out.println("Could not delete the author");
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
	
	public void deleteGenre(Genre genre) {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			
			gdao.deleteGenre(genre);
			conn.commit();
			System.out.println("Genre deleted successfully");
		} catch (Exception e) {
			System.out.println("Could not delete Genre");
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
