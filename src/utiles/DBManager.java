package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBManager {
	
	private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_URL = "jdbc:hsqldb:file:sql/testdb;shutdown=true;hsqldb.default_table_type=cached";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public static Connection connect() {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Error: - "+ e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			c = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectarse a la BD: - "+ e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		return c;
	}

	public static void shutdown() throws Exception {
		Connection c = connect();
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
	}

}
