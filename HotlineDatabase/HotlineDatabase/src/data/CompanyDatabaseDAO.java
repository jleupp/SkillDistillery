package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

public class CompanyDatabaseDAO implements DatabaseDAO {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static String url;
	private static String username;
	private static String password;
	private String sqlQuery;
	private static SQLUtils errors;
	
	@Autowired
	
	@PostConstruct
	public void init() {
		System.out.println("IN COMPANY INIT");
		try {
			//THIS REGISTERS THE MYSQL JDBC DRIVER
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("UtOH JDBC_Driver ClassNotFound");
			e.printStackTrace();
		}
	}
	
	public List<DatabaseReturn> dbConnectForQuery() {
		//Establish DB Connection
			try (Connection connection = DriverManager.getConnection(url, username, password);
					Statement statement = connection.createStatement();
					ResultSet results = statement.executeQuery(sqlQuery);) {
				ResultSetMetaData meta = results.getMetaData();
				while (results.next()) {
					for(int i =1; i<=meta.getColumnCount(); i++) {
						if (i ==1) {
							System.out.println();
						}
						if (i>1) {
							System.out.print("\t\t");
						}
						int col = meta.getColumnType(i);
						switch (col) {
						case Types.VARCHAR:
						case Types.CHAR:	System.out.print(results.getString(i));
							break;
						case Types.SMALLINT:
						case Types.INTEGER: System.out.print(results.getInt(i));
							break;
						case Types.DATE: System.out.print(results.getDate(i));
							break;
						default:
							System.out.println("COLUMN TYPE NOT MATCHED:" + meta.getColumnType(i));
							break;
						}
					}
				}
				
			} catch (SQLException e) {
				System.out.println("Caught a grenade...It has SQL written all over it");
				SQLUtils.printSQLErrors(e);
				//TODO FIND A WAY TO SEND ERROR TEXT BACK TO GOOEY
			}
			
		
		return null;
	}
	
	public void setLoginCredentials(LoginCred cred) {
		setUserName(cred.getUsername());
		setPassphrase(cred.getPassword());
		setDatabase(cred.getDatabase());
	}
	
	public void setUserName(String un) {
		CompanyDatabaseDAO.username = un;
	}
	
	public void setPassphrase(String pw) {
		CompanyDatabaseDAO.password = pw;
	}
	
	public void setDatabase(String db) {
		CompanyDatabaseDAO.url = "jdbc:mysql://localhost:3306/" + db;
	}
	
	public void setQueryStatement(String query) {
		this.sqlQuery = query;
	}
	
}
