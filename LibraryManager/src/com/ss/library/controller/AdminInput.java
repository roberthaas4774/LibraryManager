/**
 * 
 */
package com.ss.library.controller;

import java.util.List;
import java.util.Scanner;

import com.ss.library.entity.BookAuthors;
import com.ss.library.entity.BookCopies;
import com.ss.library.entity.BookLoans;
import com.ss.library.service.Admin;
import com.ss.library.service.BorrowerUser;

/**
 * @author Robert Haas
 *
 */
public class AdminInput {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void admin() {
		Admin<BookCopies> authors = new Admin<BookCopies>();
		List<BookCopies> ba = null;
		
		ba = authors.readBookCopies();
		ba.forEach(l -> {
			System.out.println(l.getBook().getTitle());
			System.out.println(l.getBook().getBookID());

//			l.getAuthor().getAuthorName();
		});
	}
}
