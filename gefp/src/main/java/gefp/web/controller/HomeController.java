package gefp.web.controller;

import gefp.model.Department;
import gefp.model.User;
import gefp.model.dao.DepartmentDao;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;


@Controller
public class HomeController {

		@Autowired
		DepartmentDao deptDao;
		
		@RequestMapping("/home.html")
		public String index()
		{
			return "home";
		}
		
		@RequestMapping("/homepage.html")
		public String logon(ModelMap models ,Department department,HttpSession session)
		{
			models.put("departments", deptDao.getDepartments());
			return "homepage";		
		}
		
		
		
}
