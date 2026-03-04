package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() throws Exception{
		String url = "jdbc:mysql://localhost:3306/QLSV";
		String user = "root";
		String password = "Thao30505@";
		
		return DriverManager.getConnection(url, user, password);
	}

}
