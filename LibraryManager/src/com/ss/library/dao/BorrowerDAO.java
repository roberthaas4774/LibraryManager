/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Borrower;

/**
 * @author Robert Haas
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower>{
	public BorrowerDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_borrower values (?, ?, ?, ?)", new Object[] {borrower.getCardNo(), borrower.getName(), 
				borrower.getAddress(), borrower.getPhoneNo()});
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("update library.tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?", 
				new Object[] {borrower.getName(), borrower.getAddress(), borrower.getPhoneNo(), borrower.getCardNo()});
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_borrower where cardNo = ?", new Object[] {borrower.getCardNo()});
	}
	
	public List<Borrower> readAllBorrowers() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_borrower", null);
	}
	
	public List<Borrower> readBorrowersByID(int id) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_borrower where cardNo = ?", new Object[] {id});
	}
	
	public List<Borrower> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List <Borrower> borrowers = new ArrayList<>();
		
		while(rs.next()) {
			Borrower b = new Borrower();
			b.setCardNo(rs.getInt("cardNo"));
			b.setName(rs.getString("name"));
			b.setAddress(rs.getString("address"));
			b.setPhoneNo(rs.getString("phone"));
			borrowers.add(b);
		}
		return borrowers;
	}
}
