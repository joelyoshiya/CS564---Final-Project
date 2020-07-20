import java.util.ArrayList;
import java.sql.*;

public class TestJDBC {
	//jdbc:mysql://localhost:3306/movieapp
    static final String databasePrefix ="movieapp";//enter database
    static final String SQLlogin ="root"; // enter username
    static final String hostName ="localhost:3306"; //TODO CHANGE if you've just pulled
    static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix+"?autoReconnect=true&useSSL=false";
    static final String SQLpassword="Your Passsword here"; // enter password TODO CHANGE if you've just pulled
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
   
    
    public void Connection(){
  
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("databaseURL"+ databaseURL);
            connection = DriverManager.getConnection(databaseURL, SQLlogin, SQLpassword);
            System.out.println("Successfully connected to the database");
         }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void simpleQuery(String sqlQuery) {
    
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery(sqlQuery);

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

    		for (int i=1; i<= columns; i++) {
    			System.out.print(metaData.getColumnName(i)+"\t");
    		}

    		System.out.println();

    		while (resultSet.next()) {
       
    			for (int i=1; i<= columns; i++) {
    				System.out.print(resultSet.getObject(i)+"\t\t");
    			}
    			System.out.println();
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public boolean verifyLogin(String username, String password) {
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select * from ProgramUser where UserName='"+username+"' and UserPassword='"+password+"';" );
    		System.out.println("select * from ProgramUser where UserName='"+username+"' and UserPassword='"+password+"';");
    		if(!resultSet.next()) {
    			return false;
    		} else {
    			System.out.println(resultSet.getObject(1));
    			return true;
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public boolean verifyNewUser(String username) {
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select * from ProgramUser where UserName='"+username+"';" );
    		
    		if(!resultSet.next()) {
    			return true;
    		} else {
    			System.out.println(resultSet.getObject(1));
    			return false;
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public void addNewUser(String username, String password, String name, int age) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    		//System.out.println("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public ArrayList<Pair> searchMovie(String movie) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select title,ID from movie where title like '%"+movie+"%';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

    		for (int i=1; i<= columns; i++) {
    			System.out.print(metaData.getColumnName(i)+"\t");
    		}

    		System.out.println();

    		while (resultSet.next()) {
       
    			for (int i=1; i<= columns; i++) {
    				System.out.print(resultSet.getObject(i)+"\t\t");
    			}
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			//System.out.print(resultSet.getObject(1)+"\t\t");
    			movies.add(p);
    			
    			System.out.println();
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
    }
    
    public ArrayList<Pair> searchPeople(String person) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select castname,actorid from person where castname like '%"+person+"%';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

    		for (int i=1; i<= columns; i++) {
    			System.out.print(metaData.getColumnName(i)+"\t");
    		}

    		System.out.println();

    		while (resultSet.next()) {
       
    			
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			//System.out.print(resultSet.getObject(1)+"\t\t");
    			people.add(p);
    			
    			System.out.println();
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
    }
    
    public void addLikedMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		//System.out.println("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void addLikedPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedPeople(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		//System.out.println("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    public void addFavMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoriteMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		//System.out.println("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    public void addFavPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoritePerson(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		//System.out.println("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String args[]) {

    	TestJDBC demoObj = new TestJDBC();
    	demoObj.Connection();
    	//String sqlQuery ="select * from student where level = 'JR';";
    	//demoObj.simpleQuery(sqlQuery);
    	System.out.println(demoObj.verifyLogin("12345", "test1"));
    	demoObj.addNewUser("user", "password", "name",20);
    	//476 login
    }
    
}
