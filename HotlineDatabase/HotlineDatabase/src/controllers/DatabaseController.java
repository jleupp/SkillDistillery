package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import data.DatabaseDAO;
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

	@RequestMapping(path="setLoginCredentials.do", method=RequestMethod.POST)
	public ModelAndView databaseLogin(@ModelAttribute("loginCredentials") LoginCred cred,
										String user_name, String password, String database) {
		System.out.println("USER-- " + user_name);
		System.out.println("Pass-- " + password);
		System.out.println("Database-- " + database);
		ModelAndView mv = new ModelAndView("hothome.jsp");
		cred.setUsername(user_name);
		cred.setPassword(password);
		cred.setDatabase(database);
		databaseDAO.setLoginCredentials(cred);
		databaseDAO.setQueryStatement("SELECT * FROM employees");
		databaseDAO.dbConnectForQuery();
		return mv;
	}
}
