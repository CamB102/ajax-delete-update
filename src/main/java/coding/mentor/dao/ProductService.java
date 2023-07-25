package coding.mentor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coding.mentor.db.util.DBUtil;
import coding.mentor.dto.Product;

public class ProductService {
	public List<Product> getBooksByCategoryId(int categoryId) throws SQLException {
		// connect to DB
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;
		List<Product> list = new ArrayList<Product>();
		try {
			// make connection to mysql
			conn = DBUtil.makeConnection();

			// -> table Category ->
			// Run query "select * from category"
			ps = conn.prepareStatement("select * from `product` where category_id = ?");
			ps.setInt(1, categoryId);

			// execute and get result SET
			rs = ps.executeQuery();

			// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
			// (Category)
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int categoryId1 = rs.getInt("category_id");
				int stock = rs.getInt("stock");
				String author = rs.getString("author");
				String title = rs.getString("title");
				int price = rs.getInt("price");

				

				product = new Product(id, name, categoryId1, author, title, stock, price);
				list.add(product);
			}
			// -> if exist -> return User(id, user,password, status)
			// -> not exist -> return null
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}

public List<Product> getAllProducts() throws SQLException {
	// connect to DB
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Product product = null;
	List<Product> list = new ArrayList<Product>();
	try {
		// make connection to mysql
		conn = DBUtil.makeConnection();

		// -> table Category ->
		// Run query "select * from category"
		ps = conn.prepareStatement("select * from `product`");

		// execute and get result SET
		rs = ps.executeQuery();

		// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
		// (Category)
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int categoryId = rs.getInt("category_id");
			int stock = rs.getInt("stock");
			String author = rs.getString("author");
			int price = rs.getInt("price");
			String title = rs.getString("title");
			

			product = new Product(id, name, categoryId, title, author, stock, price);
			list.add(product);
		}
		// -> if exist -> return User(id, user,password, status)
		// -> not exist -> return null
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	return list;
}

public Product getProductDetails(int productId) throws SQLException {
	// connect to DB
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Product product = null;
	List<Product> list = new ArrayList<Product>();
	try {
		// make connection to mysql
		conn = DBUtil.makeConnection();

		// -> table Category ->
		// Run query "select * from category"
		ps = conn.prepareStatement("select * from `product` where id = ?");
		ps.setInt(1,productId);

		// execute and get result SET
		rs = ps.executeQuery();

		// mapping data in result set (IF RESULT SET HAS DATA) into ENTITY CLASS
		// (Category)
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int categoryId = rs.getInt("category_id");
			int stock = rs.getInt("stock");
			String author = rs.getString("author");
			int price = rs.getInt("price");
			String title = rs.getString("title");
			
			

			product = new Product(id, name, categoryId, author, title, stock, price);
			list.add(product);
		}
		// -> if exist -> return User(id, user,password, status)
		// -> not exist -> return null
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	return product;
}

public void deleteProduct(int productId) throws SQLException {
	// connect to DB
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	System.out.println("product id used for db: " + productId);

	try {

		conn = DBUtil.makeConnection();

	
		ps = conn.prepareStatement("delete from `product` where id = ?");
		ps.setInt(1,productId);

		ps.execute();


	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}
public void updateProductDetails(Product product) throws SQLException {
	// connect to DB
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;


	try {
		// make connection to mysql
		conn = DBUtil.makeConnection();

		// -> table Category ->
		// Run query "select * from category"
		ps = conn.prepareStatement(" update product set name = ?, category_id = ?, title = ?, author = ?, stock = ?, price = ?"
				+ "where id = ?;");

		ps.setString(1, product.getName());
		ps.setInt(2, product.getCategoryId());
		ps.setString(3, product.getAuthor());
		ps.setString(4, product.getAuthor());
		ps.setInt(5, product.getStock());
		ps.setInt(6, product.getPrice());
		ps.setInt(7, product.getId());

		// execute and get result SET
		ps.execute();

			

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}


}
