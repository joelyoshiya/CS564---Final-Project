
import java.sql.*;

public class TestJDBC {

    static final String databasePrefix ="movieapp";//enter database
    static final String SQLlogin ="root"; // enter username
    static final String hostName ="localhost"; //washington.uww.edu
    static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix+"?autoReconnect=true&useSSL=false";
    static final String SQLpassword="@Appletoes984"; // enter password
    
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
    } // end of Connection
    
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
    } // end of simpleQuery method
    
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
    
    public static void main(String args[]) {

    	TestJDBC demoObj = new TestJDBC();
    	demoObj.Connection();
    	//String sqlQuery ="select * from student where level = 'JR';";
    	//demoObj.simpleQuery(sqlQuery);
    	System.out.println(demoObj.verifyLogin("12345", "test1"));
    	//476 login
    }
    
}
