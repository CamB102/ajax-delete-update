package coding.mentor.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection makeConnection() {
		try {
			// Connect to be4-web
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/cinema-k4", "xxx", "xxx");

			return conn;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

}
