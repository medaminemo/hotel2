import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	/**
	 * Connect to a sample database
	 */
	public static void connect() {
		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:mysql://localhost:3307/hotel";
			// create a connection to the database
			String username = "amine";
			String password = "amine";
			conn = DriverManager.getConnection(url, username, password);


			//System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		connect();
	}

}
