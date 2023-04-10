package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {
	//싱글톤 적용
		private static DBManager instance = new DBManager();
		
		private Connection conn;
		//생성자
		//	DB 접속 처리 --> JAVA 했던 JDBC 예제 참고
		private DBManager() {
			try {
				//1. Driver Class Load
				Class.forName(DBConfig.DB_DRIVER);
				//2. Connect
				conn = DriverManager.getConnection(
						DBConfig.DB_URL, DBConfig.DB_USER, DBConfig.DB_PASSWD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static DBManager getInstance() {
			if(instance==null)
				instance = new DBManager();
			return instance;
		}

		public Connection getConn() {
			return conn;
		}
		
		public void close(Statement stmt, ResultSet rs) {
			try {
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

}
