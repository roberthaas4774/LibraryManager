/**
 * 
 */
package com.ss.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import com.ss.library.entity.Author;
import com.ss.library.entity.Book;
import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.BookGenres;
import com.ss.library.entity.Genre;
import com.ss.library.entity.Publisher;
import com.ss.library.service.Admin;

/**
 * @author Robert Haas
 *
 */
public class editBook<T> {

	static Scanner scan = new Scanner(System.in);
	static Admin<Author> author = new Admin<Author>();
	static Admin<Genre> genre = new Admin<Genre>();
	static Admin<Publisher> pub = new Admin<Publisher>();
	static Admin<Book> bk = new Admin<Book>();

	static List<Author> authorList = author.readAuthors();
	static List<Genre> genreList = genre.readGenres();
	static List<Publisher> pubList = pub.readPubs();

	static Book book = new Book();
	
	public static void func(int f) { // Calls a method depending on the users input
		switch (f) {
		case 1:
			System.out.println();
			add();
			break;
		case 2:
			System.out.println();
			update();
			break;
		case 3:
			System.out.println();
			delete();
			break;
		case 4:
			System.out.println();
			read();
			break;
		case 5:
			System.out.println();
			AdminInput.admin();
			break;
		case 6:
			return;
		}
		scan.close();
	}

	public static void add() { // Adds a book to the database
		HashMap<Integer, Author> hashAuthor = new HashMap<Integer, Author>();
		HashMap<Integer, Genre> hashGenre = new HashMap<Integer, Genre>();

		AtomicInteger counter = new AtomicInteger();

		int done = authorList.size() + 1;
		int quit = done + 1;
		int auth = 0;
		int i = 0;
		
		System.out.println("Please enter the title of the book");
		String title = scan.nextLine(); // Gets the title the user inputs
		
		System.out.println();
		System.out.println("Please choose one or more Authors. Please enter the number next to the author.");
		authorList.forEach(a -> { // Prints all the authors
			System.out.println(counter.incrementAndGet() + ") " + a.getAuthorName());
		});

		System.out.println(done + ") Done choosing authors\n" + quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		while (auth < done) { // Gets all the authors the user chooses and puts them in a hash map
			i = 1;
			for (Author a : authorList) {
				if (i == auth) {
					hashAuthor.put(i, a);
				}
				i++;
			}
			auth = BaseController.getInt(quit);
		}

		if (auth == quit) {
			System.out.println();
			func(AdminInput.getFunc());
		}

		else {
			System.out.println();
			System.out.println("Please choose one or more Genres. Please enter the number next to the genre.");
			counter.set(0);
			genreList.forEach(g -> { // Prints all the genres
				System.out.println(counter.incrementAndGet() + ") " + g.getGenreName());
			});
			done = genreList.size() + 1;
			quit = done + 1;

			System.out.println(done + ") Done choosing genres\n" + quit + ") Quit to previous");
			auth = BaseController.getInt(quit);

			while (auth < done) { // Gets all the genres the user chooses and puts them in a hash map
				i = 1;
				for (Genre g : genreList) {
					if (i == auth) {
						hashGenre.put(i, g);
					}
					i++;
				}
				auth = BaseController.getInt(quit);
			}

			if (auth == quit) {
				System.out.println();
				func(AdminInput.getFunc());
			}

			else {
				System.out.println();
				System.out.println("Please choose one Publisher. Please enter the number next to the Publisher.");
				counter.set(0);
				pubList.forEach(p -> { // Prints all the publishers
					System.out.println(counter.incrementAndGet() + ") " + p.getPublisherName());
				});
				quit = pubList.size() + 1;

				System.out.println(quit + ") Quit to previous");
				auth = BaseController.getInt(quit);

				if (auth == quit) {
					System.out.println();
					func(AdminInput.getFunc());
				}

				else {
					i = 1;

					for (Publisher p : pubList) {
						if (i == auth) { // Gets the publisher the user chooses
							book.setTitle(title);
							book.setPubID(p.getPublisherID());
							bk.addBookAdmin(book, hashAuthor, hashGenre); // Sends the book info, authors, and genres and adds the book
						}
						i++;
					}
					System.out.println();
					func(AdminInput.getFunc());
				}
			}
		}
	}

	public static void update() {
		Admin<BookAuthors> bookAuth = new Admin<BookAuthors>();
		Admin<BookGenres> bookGen = new Admin<BookGenres>();

		List<Book> bookList = bk.readBooks();
		List<BookAuthors> bookAuthorList = null;
		List<BookGenres> bookGenreList = null;

		BookAuthors bookAuthors = new BookAuthors();
		BookGenres bookGenres = new BookGenres();
		
		HashMap<Integer, Author> hashAddAuthor = new HashMap<Integer, Author>();
		HashMap<Integer, Genre> hashAddGenre = new HashMap<Integer, Genre>();
		HashMap<Integer, BookAuthors> hashDeleteAuthor = new HashMap<Integer, BookAuthors>();
		HashMap<Integer, BookGenres> hashDeleteGenre = new HashMap<Integer, BookGenres>();

		AtomicInteger counter = new AtomicInteger();

		int done = 0;
		int quit = 0;
		int auth = 0;
		int i = 1;

		boolean loop = true;
		boolean prev = false;

		System.out.println("Choose a book to update. Please enter the number next to the book");
		bookList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getTitle());
		});

		quit = bookList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Book b : bookList) {
				if (auth == i) {
					book = b;
				}
				i++;
			}
			System.out.println();
			System.out.println("Please enter the new title of the book or enter 'n/a' for no change");
			String title = scan.nextLine(); // Gets the new title of the book
			System.out.println();
			while (loop) {
				System.out.println();
				System.out.println(
						"Would you like to edit the authors?\n1) Add authors to the book\n2) Remove authors from the book\n3) Go to next"
								+ "\n4) Quit to previous");
				counter.set(0);
				switch (BaseController.getInt(4)) {
				case 1: // Adding more authors to the book
					System.out
							.println("Please choose one or more Authors. Please enter the number next to the author.");
					authorList.forEach(a -> {
						System.out.println(counter.incrementAndGet() + ") " + a.getAuthorName());
					});
					done = authorList.size() + 1;
					quit = done + 1;

					System.out.println(done + ") Done choosing authors\n" + quit + ") Quit to previous");
					auth = BaseController.getInt(quit);

					while (auth < done) {
						i = 1;
						for (Author a : authorList) {
							if (i == auth) { // Adds the authors the user chooses to a hash map
								hashAddAuthor.put(i, a);
							}
							i++;
						}
						auth = BaseController.getInt(quit);
					}
					break;

				case 2: // Removing authors from the book
					System.out.println("Please choose one or more Authors to remove, please leave at least one. "
							+ "Please enter the number next to the author.");
					bookAuthorList = bookAuth.readBookAuthorsByBookId(book.getBookID());

					bookAuthorList.forEach(a -> {
						System.out.println(counter.incrementAndGet() + ") " + a.getAuthor().getAuthorName());
					});
					done = bookAuthorList.size() + 1;
					quit = done + 1;

					System.out.println(done + ") Done choosing authors\n" + quit + ") Quit to previous");
					auth = BaseController.getInt(quit);
					while (auth < done) {
						i = 1;
						for (BookAuthors a : bookAuthorList) {
							if (i == auth) { // Adds the authors the user chooses to a hash map
								hashDeleteAuthor.put(i, a);
							}
							i++;
						}
						auth = BaseController.getInt(quit); // Gets the input from the user
					}
					break;

				case 3: // Do nothing
					loop = false;
					break;

				case 4: // Go to previous menu
					prev = true;
					loop = false;
					break;
				}
			}

			if (!prev) { // Goes to the previous menu if prev is set to true
				loop = true;
				while (loop) {
					System.out.println(
							"Would you like to edit the genres?\n1) Add genres to the book\n2) Remove genres from the book\n3) Go to next"
									+ "\n4) Quit to previous");
					counter.set(0);

					switch (BaseController.getInt(4)) {
					case 1: // Adding more genres to the book
						System.out.println(
								"Please choose one or more Genres. Please enter the number next to the genre.");
						genreList.forEach(g -> {
							System.out.println(counter.incrementAndGet() + ") " + g.getGenreName());
						});
						done = genreList.size() + 1;
						quit = done + 1;

						System.out.println(done + ") Done choosing genres\n");
						auth = BaseController.getInt(done);

						while (auth < done) {
							i = 1;
							for (Genre g : genreList) {
								if (i == auth) { // Adds the genres the user chooses to a hash map
									hashAddGenre.put(i, g);
								}
								i++;
							}
							auth = BaseController.getInt(quit);
						}

						break;

					case 2: // Removing genres from the book
						System.out.println(
								"Please choose one or more Genres to remove, Please enter the number next to the genre.");
						bookGenreList = bookGen.readBookGenresByBookId(book.getBookID());

						bookGenreList.forEach(g -> {
							System.out.println(counter.incrementAndGet() + ") " + g.getGenre().getGenreName());
						});
						done = bookGenreList.size() + 1;
						quit = done + 1;

						System.out.println(done + ") Done choosing genres\n" + quit + ") Quit to previous");
						auth = BaseController.getInt(quit);
						while (auth < done) {
							i = 1;
							for (BookGenres g : bookGenreList) {
								if (i == auth) { // Adds the genres the user chooses to a hash map
									hashDeleteGenre.put(i, g);
								}
								i++;
							}
							auth = BaseController.getInt(quit); // Gets the users input
						}

						break;

					case 3: // Do nothing
						loop = false;
						break;

					case 4: // Go to previous menu
						prev = true;
						loop = false;
						break;
					}
				}
				
				System.out.println();
				if (!prev) { // Goes to the previous menu if prev is set to true
					System.out.println("Would you like to change the publisher?\n1) Yes\n2) No\n3) Quit to previous");
					auth = BaseController.getInt(3);
					if (auth != 3) {
						if (auth == 1) {
							System.out.println(
									"Please choose one Publisher. Please enter the number next to the Publisher.");
							
							counter.set(0);
							pubList.forEach(p -> { // Prints the publishers
								System.out.println(counter.incrementAndGet() + ") " + p.getPublisherName());
							});
							quit = pubList.size() + 1;

							System.out.println(quit + ") Quit to previous");
							auth = BaseController.getInt(quit);

							if (auth != quit) {
								i = 1;
								for (Publisher p : pubList) {
									if (i == auth) {
										book.setPubID(p.getPublisherID()); // Gets the publishers id and sets it to the pubId in the book object
									}
									i++;
								}
							}
						}
						
						if (auth == 2) {
							if (!"n/a".equals(title.toLowerCase())) {
								book.setTitle(title);
							}
							bk.updateBook(book);
							
							for (Map.Entry<Integer, Author> element : hashAddAuthor.entrySet()) { // Adds all the authors the user chose to the book
								bookAuthors.setAuthor(element.getValue());
								bookAuthors.setBook(book);
								bookAuth.addBookAuthor(bookAuthors);
							}

							for (Map.Entry<Integer, Genre> element : hashAddGenre.entrySet()) { // Adds all the genres the user chose to the book 
								bookGenres.setGenre(element.getValue());
								bookGenres.setBook(book);
								bookGen.addBookGenre(bookGenres);
							}
							
							for (Map.Entry<Integer, BookAuthors> element : hashDeleteAuthor.entrySet()) { // Removes all the authors the user chose
																											// from the book
								bookAuthors.setAuthor(element.getValue().getAuthor());
								bookAuthors.setBook(book);
								bookAuth.deleteBookAuthor(bookAuthors);
							}

							for (Map.Entry<Integer, BookGenres> element : hashDeleteGenre.entrySet()) { // Removes all the genres the user chose
																										// from the book
								bookGenres.setGenre(element.getValue().getGenre());
								bookGenres.setBook(book);
								bookGen.deleteBookGenre(bookGenres);
							}
						}
					} 
				}
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void delete() { // Deletes the book from the database
		Admin<Book> bk = new Admin<Book>();

		List<Book> bookList = bk.readBooks();

		AtomicInteger counter = new AtomicInteger();

		int quit = 0;
		int auth = 0;
		int i = 1;
		
		System.out.println();
		System.out.println("Choose a book to delete. Please enter the number next to the book");
		bookList.forEach(b -> {
			System.out.println(counter.incrementAndGet() + ") " + b.getTitle());
		});

		quit = bookList.size() + 1;
		System.out.println(quit + ") Quit to previous");
		auth = BaseController.getInt(quit);

		if (auth != quit) {
			for (Book b : bookList) {
				if (auth == i) {
					bk.deleteBook(b); // Removes the chosen book from the database
				}
				i++;
			}
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void read() { // Prints all the books and all the data associated with it
		Admin<Book> bk = new Admin<Book>();
		Admin<BookAuthors> bookAuthor = new Admin<BookAuthors>();
		Admin<BookGenres> bookGenre = new Admin<BookGenres>();
		Admin<Publisher> publisher = new Admin<Publisher>();

		List<Book> bookList = bk.readBooks();

		System.out.println("-------------------------------------------");
		for (Book b : bookList) {
			System.out.println("Title: " + b.getTitle());

			System.out.print("Author/Authors: ");
			readAuthors(bookAuthor.readBookAuthorsByBookId(b.getBookID()));

			System.out.print("Genre/Genres: ");
			readGenres(bookGenre.readBookGenresByBookId(b.getBookID()));

			System.out.print("Publisher: " + publisher.readPubById(b.getPubID()).get(0).getPublisherName());
			System.out.println("\n-------------------------------------------");
		}
		System.out.println();
		func(AdminInput.getFunc());
	}

	public static void readAuthors(List<BookAuthors> list) { // Prints all the authors associated with the book
		int i = 0;

		for (BookAuthors ba : list) {
			System.out.print(ba.getAuthor().getAuthorName());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}

	public static void readGenres(List<BookGenres> list) { // Prints all the genres associated with the book
		int i = 0;

		for (BookGenres bg : list) {
			System.out.print(bg.getGenre().getGenreName());
			if (i != list.size() - 1) {
				System.out.print(", ");
			}
			i++;
		}
		System.out.println();
	}
}
