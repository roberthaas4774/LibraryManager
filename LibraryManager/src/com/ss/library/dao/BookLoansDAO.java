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
import com.ss.library.entity.Borrower;
import com.ss.library.entity.LibraryBranch;

/**
 * @author Robert Haas
 *
 */
public class BookLoansDAO extends BaseDAO<BookLoans>{
	public BookLoansDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addBookLoan(BookLoans loan) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_book_loans values (?, ?, ?, ?, ?, ?)", new Object[] {loan.getBook().getBookID(),
				loan.getBranch().getBranchID(), loan.getBorrower().getCardNo(), loan.getDateOut(), loan.getDateDue(), loan.getDateIn()});
	}

	public void updateBookLoan(BookLoans loan) throws ClassNotFoundException, SQLException {
		save("update library.tbl_book_loans set dateOut = ?, dueDate = ?, dateIn = ? where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[] {loan.getDateOut(), loan.getDateDue(), loan.getDateIn(), loan.getBook().getBookID(),
						loan.getBranch().getBranchID(), loan.getBorrower().getCardNo()});
	}

	public void deleteBookLoan(BookLoans loan) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?", new Object[] {
				loan.getBook().getBookID(), loan.getBranch().getBranchID(), loan.getBorrower().getCardNo()});
	}
	
	public List<BookLoans> readAllBookLoans() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_loans natural join library.tbl_book natural join library.tbl_library_branch"
				+ " natural join library.tbl_borrower", null);
	}
	
	public List<BookLoans> readBookLoanByID(BookLoans loan) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_loans natural join library.tbl_book natural join library.tbl_library_branch"
				+ " natural join library.tbl_borrower where bookId = ? and branchId = ? and cardNo = ?", new Object[] {
				loan.getBook().getBookID(), loan.getBranch().getBranchID(), loan.getBorrower().getCardNo()});
	}
	
	public List<BookLoans> readBookLoanByPerson(int cardNo) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_loans natural join library.tbl_book natural join library.tbl_library_branch "
				+ "natural join library.tbl_borrower where cardNo = ?", new Object[] {cardNo});
	}
	
	public List<BookLoans> readBookCopiesByBookId(int b) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_book_copies natural join library.tbl_book natural join library.tbl_library_branch"
				+ " where bookId = ?", new Object[] {b});
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookLoans> loans = new ArrayList<>();
		while(rs.next()) {
			BookLoans bl = new BookLoans();
			Book book = new Book();
			LibraryBranch lib = new LibraryBranch();
			Borrower bor = new Borrower();
			
			book.setBookID(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setPubID(rs.getInt("pubId"));
			bl.setBook(book);
			
			lib.setBranchID(rs.getInt("branchId"));
			lib.setBranchName(rs.getString("branchName"));
			lib.setBranchAddress(rs.getString("branchAddress"));
			bl.setBranch(lib);
			
			bor.setCardNo(rs.getInt("cardNo"));
			bor.setName(rs.getString("name"));
			bor.setAddress(rs.getString("address"));
			bor.setPhoneNo(rs.getString("phone"));
			bl.setBorrower(bor);
			
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDateDue(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));
			loans.add(bl);
		}
		return loans;
	}
}
