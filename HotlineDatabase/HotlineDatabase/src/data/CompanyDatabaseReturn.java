package data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CompanyDatabaseReturn implements DatabaseReturn {
	private ResultSetMetaData meta;
	private List<String> tableList;
	private Integer rowsReturned;
	
	
	public CompanyDatabaseReturn() {
		
	}
	
	public CompanyDatabaseReturn(ResultSet rs) { //throws SQLException {   //possibly throw later and have controller handle exceptions
		System.out.println("IN database RESULTSET CONSTRUCTOR");
		try {
			meta = rs.getMetaData();
			tableList = new ArrayList<>();
			tableList.add("<tr>");
			for(int i = 1; i<=meta.getColumnCount(); i++) {
				tableList.add("<th>" + meta.getColumnLabel(i) + "</th>");
			}
			tableList.add("</tr>");
			
			while (rs.next()) {
				tableList.add("<tr>");
				for(int i=1; i<=meta.getColumnCount(); i++) {
					int col = meta.getColumnType(i);
					switch (col) {
					case -1:
					case Types.LONGNVARCHAR:
					case Types.VARCHAR:
					case Types.CHAR:	tableList.add("<td>" + rs.getString(i)+ "</td>");
						break;
					case Types.SMALLINT:
					case Types.INTEGER: tableList.add("<td>" + rs.getInt(i) + "</td>");
						break;
					case Types.DATE: tableList.add("<td>" + rs.getDate(i) + "</td>");
						break;
					default:
						System.out.println("COLUMN TYPE NOT MATCHED:" + meta.getColumnType(i));
						tableList.add("<td>BIG FRIGGIN MISTAKE</td>");
						break;
					}
				}
				tableList.add("</tr>");
			}
			rs.last();
			this.rowsReturned = rs.getRow();
			System.out.println("NUMBER OF ROWS RETURNED:  " + rowsReturned);
			System.out.println("NUMBER OF rows in tableList: " + tableList.size());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Caught a grenade over in the DatabaseReturn Object");
		}
	}

	public List<String> getTableList() {
		return tableList;
	}

	public Integer getRowsReturned() {
		return rowsReturned;
	}

	public void setTableList(List<String> tableList) {
		this.tableList = tableList;
	}

	public void setRowsReturned(Integer rowsReturned) {
		this.rowsReturned = rowsReturned;
	}
	
	
}
