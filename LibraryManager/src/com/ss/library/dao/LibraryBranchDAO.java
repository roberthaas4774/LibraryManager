/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.LibraryBranch;

/**
 * @author Robert Haas
 *
 */
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> {
	public LibraryBranchDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public void addLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_library_branch values (?, ?, ?)", new Object[] {libBranch.getBranchID(), libBranch.getBranchName(), libBranch.getBranchAddress()});
	}

	public void updateLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		save("update library.tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", 
				new Object[] {libBranch.getBranchName(), libBranch.getBranchAddress(), libBranch.getBranchID()});
	}

	public void deleteLibraryBranch(LibraryBranch libBranch) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_library_branch where branchId = ?", new Object[] {libBranch.getBranchID()});
	}
	
	public List<LibraryBranch> readAllLibraryBranches() throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_library_branch", null);
	}
	
	public List<LibraryBranch> readLibraryBranchesByID(LibraryBranch libBranch) throws ClassNotFoundException, SQLException{
		return read("select * from library.tbl_library_branch where branchId = ?", new Object[] {libBranch.getBranchID()});
	}
	
//	public List<LibraryBranch> readCopies(LibraryBranch libBranch) throws ClassNotFoundException, SQLException{
//		return read("select noOfCopies from library.tbl_book_copies natural join library.tbl_book natural join library.tbl_library_branch"
//				+ " where branchId = ? and bookId = ?", new Object[] {libBranch.getBranchID(), libBranch.getBookCopies()});
//	}
	
	public List<LibraryBranch> extractData(ResultSet rs) throws ClassNotFoundException, SQLException{
		List<LibraryBranch> libBranches = new ArrayList<>();

		while(rs.next()) {
			LibraryBranch lb = new LibraryBranch();	
			lb.setBranchID(rs.getInt("branchId"));
			lb.setBranchName(rs.getString("branchName"));
			lb.setBranchAddress(rs.getString("branchAddress"));
			libBranches.add(lb);
		}
		return libBranches;
	}
}
