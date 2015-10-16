package gefp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.testng.internal.PackageUtils;

import gefp.model.Checkpoint;
import gefp.model.Department;
import gefp.model.Plan;
import gefp.model.User;
import gefp.model.dao.CheckpointDao;
import gefp.model.dao.DepartmentDao;
import gefp.model.dao.PlanDao;
import gefp.model.dao.UserDao;
import gefp.web.validator.PasswordValidator;
import gefp.web.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PlanDao planDao;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private CheckpointDao checkpointDao;

	@Autowired
	UserValidator userValidator;

	@Autowired
	PasswordValidator passwordValidator;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String login(ModelMap models) {
		models.put("user", new User());
		return "login";
	}

	@RequestMapping("/studentView.html")
	public String studentView(HttpSession session, ModelMap models,
			@RequestParam int id) {
		
		User user = userDao.getUserById(id);
		Plan plan = planDao.getPlan(user.getPlan().getId());
		session.setAttribute("UID", user.getId());
		models.put("user", user);
		models.put("plan", plan);
		return "studentView";

	}

	@RequestMapping("saveCheckpoint.html")
	public String saveCheckpoint(HttpSession session,
			HttpServletRequest request, ModelMap models,
			@RequestParam int checkpoints, @RequestParam boolean flag) {
		
		int userId = Integer.parseInt(session.getAttribute("UID").toString());
		User user = userDao.getUserById(userId);
		Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpoints);
		Set<Checkpoint> list = user.getCheckpoints();
		if (flag == true) {
			list.add(checkpoint);
		} else {
			list.remove(checkpoint);
		}
		user.setCheckpoints(list);
		userDao.saveUser(user);
		return "studentView.html?id=" + userId;

	}

	@RequestMapping(value = "/editProfile.html", method = RequestMethod.GET)
	public String editProfile(HttpSession session, ModelMap models,
			@RequestParam int id) {
		User user = userDao.getUserById(id);
		models.put("departments", departmentDao.getDepartments());
		models.put("user", user);
		return "editProfile";

	}

	@RequestMapping(value = "/editProfile.html", method = RequestMethod.POST)
	public String editProfile(HttpSession session, HttpServletRequest request) {
		int userId = Integer
				.parseInt(request.getParameter("userId").toString());
		int deptId = Integer.parseInt(request.getParameter("newDepartment")
				.toString());
		User user = userDao.getUserById(userId);
		Department department = departmentDao.getDepartment(deptId);
		user.setEmailId(request.getParameter("emailId").toString());
		user.setMajor(department);
		user.setPlan(department.getCurrentPlan());
		userDao.saveUser(user);
		return "success";

	}

	@RequestMapping(value = "/changePassword.html")
	public String changePassword(HttpSession session, ModelMap models,
			@RequestParam int id) {
		session.setAttribute("UID", id);
		models.put("user", new User());
		return "changePassword";

	}

	@RequestMapping(value = "/changePassword.html", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute User user,
			HttpSession session, HttpServletRequest request,
			BindingResult bindingResult) {

		if (user.getUsername().equals(""))
			return "success";

		passwordValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors())
			return "changePassword";

		int userId = Integer.parseInt(session.getAttribute("UID").toString());

		User newUser = userDao.getUserById(userId);
		newUser.setPassword(user.getUsername());
		userDao.saveUser(newUser);

		return "success";

	}

	@RequestMapping("/success.html")
	public String success(HttpSession session) {
		int id = (int) session.getAttribute("UID");
		return "redirect:studentView.html?id=" + id;
	}

	@RequestMapping(value = "/advisor/advisorHomepage.html")
	public String advisorHomepage(@RequestParam int advisor_id, ModelMap models) {
		User user = userDao.getUserById(advisor_id);
		models.put("user", user);
		return "advisorHomepage";
	}

	@RequestMapping(value = "/advisor/search.html")
	public String search(@RequestParam(required = false) String term,
			@RequestParam(required = false) String dept, ModelMap models) {
		List<User> users = null;
		List<User> filteredUser = new ArrayList<User>();
		if (StringUtils.hasText(term)) {
			users = userDao.searchUsers(term);
		}
		for (User user : users) {
			if (user.hasRole("ROLE_STUDENT")) {
				filteredUser.add(user);
			}

		}
		models.addAttribute("users", filteredUser);
		return "advisorHomepage";
	}

	@RequestMapping("/403.html")
	public String accessDeniedHandler() {
		return "403";
	}

	@RequestMapping("/401.html")
	public String loginDeniedHandler() {
		return "401";
	}
}