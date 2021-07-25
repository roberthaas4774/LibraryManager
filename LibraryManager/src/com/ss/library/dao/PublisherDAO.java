/**
 * 
 */
package com.ss.library.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.library.entity.Publisher;

/**
 * @author Robert Haas
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addPublisher(Publisher pub) throws ClassNotFoundException, SQLException {
		save("insert into library.tbl_publisher values (?, ?, ?, ?)", new Object[] {pub.getPublisherID(), pub.getPublisherName(), 
				pub.getPublisherAddress(), pub.getPublisherPhone()});
	}

	public void updatePublisher(Publisher pub) throws ClassNotFoundException, SQLException {
		save("update library.tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?",
				new Object[] {pub.getPublisherName(), pub.getPublisherAddress(),
						pub.getPublisherPhone(), pub.getPublisherID()});
	}

	public void deletePublisher(Publisher pub) throws ClassNotFoundException, SQLException {
		save("delete from library.tbl_publisher where publisherId = ?", new Object[] {pub.getPublisherID()});
	}

	public List<Publisher> readAllPublishers() throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_publisher", null);
	}
	
	public List<Publisher> readAllPublishersById(int id) throws ClassNotFoundException, SQLException {
		return read("select * from library.tbl_publisher where publisherId = ?", new Object[] {id});
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = new ArrayList<>();

		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherID(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

}
