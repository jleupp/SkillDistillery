package data;

import java.util.List;

public interface DatabaseDAO {
	public void setUserName(String user_name);
	public void setPassphrase(String pw);
	public void setQueryStatement(String query);
	public void setDatabase(String database);
	public void setLoginCredentials(LoginCred cred);
	public List<DatabaseReturn> dbConnectForQuery();
}
