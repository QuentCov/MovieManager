package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static final String URL = "jdbc:mysql://cse.unl.edu:3306/qcovert";
    private static final String USER = "qcovert";
    private static final String PASS = "???";
    
    public static ResultSet runQuery(String query) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            connection.close();
            return rs;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    public static int runUpdate(String query) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
	        Statement stmt = connection.createStatement();
	        int i = stmt.executeUpdate(query);
	        connection.close();
	        return i;
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
}
