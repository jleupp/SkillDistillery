package controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.CompanyDatabaseReturn;
import data.DatabaseDAO;
import data.DatabaseReturn;
import data.LoginCred;

@Controller
@SessionAttributes({"prevCommands", "loginCredentials"})
public class DatabaseController {
	@Autowired
	DatabaseDAO databaseDAO;
	
	@ModelAttribute("prevCommands")
	public List<String> initCommandHistory() {
		List<String> cmdHistory = new ArrayList<>();
		return cmdHistory;
	}
	
	@ModelAttribute("loginCredentials")
	public LoginCred initLogin() {
		LoginCred cred = new LoginCred();
		return cred;
	}
	
	@RequestMapping(path="dbUpdate.do", method=RequestMethod.POST)
	public ModelAndView databaseUpdate(@ModelAttribute("prevCommands") List<String> cmdHistory,
										String update) {
		System.out.println("IN duUPDATE String " );
		cmdHistory.add(0, databaseDAO.setQueryStatement(update));
		ModelAndView mv = new ModelAndView("hothome.jsp");
		Integer numberRowsUpdated = databaseDAO.dbConnectForUpdate();
		if (numberRowsUpdated != null) {
			mv.addObject("rowsUpdated", numberRowsUpdated);
		} else {
			//Null because a SQL exception happened figure out how to get to gooey
			System.out.println("IN CONTROLLER QUERY ELSE...NULL WAS RETURNED");
		}
		return mv;
	}
	
	@RequestMapping(path="dbQuery.do", method=RequestMethod.POST)
	public ModelAndView databaseQuery(@ModelAttribute("prevCommands") List<String> cmdHistory,
			String query) {
		cmdHistory.add(0, databaseDAO.setQueryStatement(query));
		ModelAndView mv = new ModelAndView("returnset.jsp");
		DatabaseReturn tablefied = databaseDAO.dbConnectForQuery();
		if (tablefied != null) {
			mv.addObject("rowsUpdated", tablefied.getRowsReturned());
			mv.addObject("tableList", tablefied.getTableList() );
		} else {
			//Null because a SQL exception happened figure out how to get to gooey
			System.out.println("IN CONTROLLER QUERY ELSE...NULL WAS RETURNED");
		}
		return mv;
	}

	@RequestMapping(path="setLoginCredentials.do", method=RequestMethod.POST)
	public ModelAndView databaseLogin(@ModelAttribute("loginCredentials") LoginCred cred,
										String user_name, String password, String database) {
//		System.out.println("USER-- " + user_name);
//		System.out.println("Pass-- " + password);
//		System.out.println("Database-- " + database);
		ModelAndView mv = new ModelAndView("hothome.jsp");
		cred.setUsername(user_name);
		cred.setPassword(password);
		cred.setDatabase(database);
		databaseDAO.setLoginCredentials(cred);
//		databaseDAO.setQueryStatement("SELECT * FROM employees");
//		databaseDAO.dbConnectForQuery();
		return mv;
	}
}
