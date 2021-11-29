
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Jdbc {
	
	public static void createDatabase(String DBname) {

        String url = "jdbc:sqlite:C:/sqlite/" + DBname;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Database created");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void createTable() {
        String url = "jdbc:sqlite:C:/sqlite/Sample.db";
        
        String sql = "CREATE TABLE IF NOT EXISTS movies(\n"
				+"id integer PRIMARY KEY,\n"
				+"Name text NOT NULL,\n"
				+"Actor text NOT NULL,\n"
				+"Actress text NOT NULL,\n"
				+"Year integer NOT NULL,\n"
				+"Director text NOT NULL\n"
				+");";
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement statement = conn.createStatement()) {
        	statement.execute(sql);
        	System.out.println("Table created");
        	}
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void insert(int id, String name, String actor, String actress, int year, String director) {
		
		String url = "jdbc:sqlite:C:/sqlite/Sample.db";
		
		String sql = "INSERT INTO movies(id, Name, Actor, Actress, Year, Director) \n"
				+"VALUES ("+id+",'"+name+"','"+actor+"','"+actress+"',"+year+",'"+director+"');";
		
		System.out.println(sql);
		
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			statement.execute(sql);
			System.out.println("Inserted record " + id + " in Movie Table");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void query(String Actor) {
        String url = "jdbc:sqlite:C:/sqlite/Sample.db";
        
        String sql = "SELECT id, Name, Actor, Actress, Year, Director FROM movies WHERE Actor= '"+Actor+"';";			
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement statement = conn.createStatement();
        		ResultSet result    = statement.executeQuery(sql)) {
				while (result.next()) {
				   System.out.println(result.getInt("id") +  "\t" + 
				   result.getString("Name") + "\t" +
				   result.getString("Actor") + "\t" +
				   result.getString("Actress") + "\t" +
				   result.getInt("Year") + "\t" +
				   result.getString("director"));
				   }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public static void query() {
        String url = "jdbc:sqlite:C:/sqlite/Sample.db";
        
        String sql = "SELECT id, Name, Actor, Actress, Year, Director FROM Movies";				
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement statement = conn.createStatement();
        		ResultSet result    = statement.executeQuery(sql)) {
				while (result.next()) {
				   System.out.println(result.getInt("id") +  "\t" + 
				   result.getString("Name") + "\t" +
				   result.getString("Actor") + "\t" +
				   result.getString("Actress") + "\t" +
				   result.getInt("Year") + "\t" +
				   result.getString("director"));
				   }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	

	 public static void main(String[] args) { 
		 createDatabase("Sample.db");
		 createTable();
		 insert(101,"Jungle Cruise","Dwayne Johnson","Emily Blunt",2021,"Jaume Collet-Serra");
		 insert(102,"Shershaah"," Sidharth Malhotra","Kiara Advani",2021," Vishnuvardhan");
		 insert(103,"K.G.F","Yash","Srinidhi Shetty",2018,"Prashanth Neel");
		 query();
		 query("Dwayne Johnson");
	    }
	
}




