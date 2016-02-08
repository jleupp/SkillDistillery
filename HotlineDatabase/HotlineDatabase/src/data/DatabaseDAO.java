package data;

import java.sql.ResultSet;

public interface DatabaseDAO {
	public void setUserName(String user_name);
	public void setPassphrase(String pw);
	public String setQueryStatement(String query);
	public void setDatabase(String database);
	public void setLoginCredentials(LoginCred cred);
	public DatabaseReturn dbConnectForQuery();
	public Integer dbConnectForUpdate();
}
