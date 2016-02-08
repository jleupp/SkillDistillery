package data;

import java.util.List;

public interface DatabaseReturn {

	public List<String> getTableList();
	public Integer getRowsReturned();
	public void setTableList(List<String> tableList);
	public void setRowsReturned(Integer rowsReturned);
	
}
