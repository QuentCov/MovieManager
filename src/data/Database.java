package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private static final String URL = "jdbc:mysql://cse.unl.edu:3306/qcovert";
    private static final String USER = "qcovert";
    private static final String PASS = "qJ3zTz";
    
    private static boolean createdDB = true;
    
    public static int setupDatabase() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
	    	Statement stmt = connection.createStatement();

	    	// unfortunately needs absolute path of the Setup.sql file
	    	BufferedReader setupScriptReader = new BufferedReader(new FileReader("C:/Users/Quentin Covert/MovieManager/src/data/Setup.sql"));
	    	String query = "";
	    	int i = 0;
	    	while((query = setupScriptReader.readLine()) != null) {
	    		i = stmt.executeUpdate(query);
	    	}
        	createdDB = true;
        	
        	connection.close();
            stmt.close();
            setupScriptReader.close();
            return i;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
    public static Connection getConnection() {
    	Connection conn = null;
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return conn;
    }
    
    public static PreparedStatement prepareStatement(String query){ 
        if(!createdDB) { 
            setupDatabase(); 
        } 
        PreparedStatement statement = null; 
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(URL, USER, PASS); 
            statement = conn.prepareStatement(query); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } catch (ClassNotFoundException e1) { 
            e1.printStackTrace(); 
        } 
        return statement; 
    }
    
    public static PreparedStatement prepareStatement(Connection c, String query){
		if(!createdDB) {
			setupDatabase();
        }
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			statement = c.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return statement;
    }
    
    public static PreparedStatement prepareStatement(Connection c, String query, ArrayList<String> params){
		if(!createdDB) {
			setupDatabase();
        }
		PreparedStatement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			statement = c.prepareStatement(query);
			for(int j = 1; j < params.size() + 1; j++) {
	        	statement.setString(j, params.get(j-1));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return statement;
    }
    
    public static int runUpdate(String query) {
		try {
			if(!createdDB) {
    			setupDatabase();
            }
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
	    	PreparedStatement stmt = connection.prepareStatement(query);
	        int i = stmt.executeUpdate();
	        connection.close();
	        stmt.close();
	        return i;
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
    public static int runUpdate(String query, ArrayList<String> params) {
    	try {
    		if(!createdDB) {
    			setupDatabase();
            }
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
	        PreparedStatement stmt = connection.prepareStatement(query);
	        for(int j = 1; j < params.size() + 1; j++) {
	        	stmt.setString(j, params.get(j-1));
	        }
	        int i = stmt.executeUpdate();
	        connection.close();
	        stmt.close();
	        return i;
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
    // specific method for adding blob data like images
    public static int runUpdate(String query, ArrayList<String> params, InputStream stream, int blobIndex) {
    	try {
    		if(!createdDB) {
    			setupDatabase();
            }
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection connection = DriverManager.getConnection(URL, USER, PASS);
	        PreparedStatement stmt = connection.prepareStatement(query);
	        for(int j = 1; j < params.size() + 1; j++) {
	        	if(j != blobIndex) {
		        	stmt.setString(j, params.get(j-1));
	        	} else {
		        	stmt.setBlob(j, stream);
	        	}
	        }
	        int i = stmt.executeUpdate();
	        connection.close();
	        stmt.close();
	        return i;
		} catch (SQLException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
    }
    
}
