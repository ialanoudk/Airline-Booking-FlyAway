package flyaway;


import java.sql.*; 

public class MySQLDB {
	
	public static Connection getConnection() throws SQLException,ClassNotFoundException{
		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/projects2021_flyaway";
		String user = "root";
		String password= "root";  
		Class.forName(driverClassName); 
		Connection connection = DriverManager.getConnection(url,user,password);
		return connection;
	}
	 

}