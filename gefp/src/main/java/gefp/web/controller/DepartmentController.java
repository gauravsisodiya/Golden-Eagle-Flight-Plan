package gefp.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gefp.model.Cell;
import gefp.model.Checkpoint;
import gefp.model.Department;
import gefp.model.Plan;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.User;
import gefp.model.dao.CellDao;
import gefp.model.dao.CheckpointDao;
import gefp.model.dao.DepartmentDao;
import gefp.model.dao.PlanDao;
import gefp.model.dao.RunwayDao;
import gefp.model.dao.StageDao;
import gefp.model.dao.UserDao;
import gefp.security.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CellDao cellDao;

	@Autowired
	private StageDao stageDao;

	@Autowired
	private RunwayDao runwayDao;

	@Autowired
	private CheckpointDao checkpointDao;

	@Autowired
	private PlanDao planDao;

	@RequestMapping("/department.html")
	public String showDepartment(Integer id, ModelMap models,
			HttpSession session) {
		User user = userDao.getUserById(SecurityUtils.getUser().getId());

		if (!user.hasRole("ROLE_STUDENT")) {
			models.put("department", departmentDao.getDepartment(id));
			return "department";
		} else {
			if (user.getMajor().getId() == id) {
				models.put("user", user);
				models.put("department", departmentDao.getDepartment(id));
				return "department";
			}
			return "403";
		}
	}

	@RequestMapping("/plan.html")
	public String showplan(Integer id, ModelMap models, HttpSession session) {

		User user = userDao.getUserById(SecurityUtils.getUser().getId());

		if (user.hasRole("ROLE_ADMIN")) {
			for(Runway r : planDao.getPlan(id).getRunways())
			models.put("department", departmentDao.getDepartment(id));
			models.put("plan", planDao.getPlan(id));
			return "plan";
		} else {
			if (user.hasRole("ROLE_STUDENT")) {
				Department department = departmentDao
						.getDepartment(SecurityUtils.getUser().getMajor()
								.getId());
				List<Plan> listPlan = department.getPlans();
				if (listPlan.contains(planDao.getPlan(id))
						&& planDao.getPlan(id).getPublishedDate() != null) {
					models.put("department", departmentDao.getDepartment(id));
					models.put("plan", planDao.getPlan(id));
					return "plan";
				}

			} else {
				if (planDao.getPlan(id).getPublishedDate() != null) {
					models.put("department", departmentDao.getDepartment(id));
					models.put("plan", planDao.getPlan(id));
					return "plan";
				}
			}
			return "403";
		}
	}

	@RequestMapping(value = "/admin/addPlan.html", method = RequestMethod.GET)
	public String addPlan(Integer department_id, ModelMap models,
			HttpSession session) {
		models.put("plan", new Plan());
		models.put("department", departmentDao.getDepartment(department_id));
		return "addPlan";
	}

	@RequestMapping(value = "/admin/addPlan.html", method = RequestMethod.POST)
	public String addPlan(@ModelAttribute Plan plan, Integer department_id,
			HttpSession session) {

		Plan modifiedPlan = planDao.savePlan(plan);
		Department dept = departmentDao.getDepartment(department_id);
		modifiedPlan.setDepartment(dept);
		planDao.savePlan(modifiedPlan);
		session.setAttribute("currentPlan", modifiedPlan);
		session.setAttribute("department",
				departmentDao.getDepartment(department_id));

		return "redirect:../plan.html?id=" + modifiedPlan.getId();
	}

	@RequestMapping(value = "/admin/selectRunway.html", method = RequestMethod.GET)
	public String selectRunway(ModelMap models, @RequestParam Integer plan_id,
			HttpSession session) {
		models.put("plan", planDao.getPlan(plan_id));
		models.put("runways", runwayDao.getRunways());
		return "selectRunway";

	}

	@RequestMapping(value = "/admin/selectRunway.html", method = RequestMethod.POST)
	public String selectRunway(@ModelAttribute Plan plan, Integer plan_id,
			HttpServletRequest request, HttpSession session) {

		Integer runway_id = Integer.parseInt(request.getParameter("runway")
				.toString());
		Runway addRunway = runwayDao.getRunway(runway_id);

		Plan newPlan = planDao.getPlan(plan_id);
		List<Runway> runwayList = newPlan.getRunways();
		runwayList.add(addRunway);
		newPlan.setRunways(runwayList);
		planDao.savePlan(newPlan);

		return "redirect:../plan.html?id=" + plan_id;

	}

	@RequestMapping(value = "/admin/selectStage.html", method = RequestMethod.GET)
	public String selectStage(ModelMap models, @RequestParam Integer plan_id,
			HttpSession session) {

		models.put("plan", planDao.getPlan(plan_id));
		models.put("stages", stageDao.getStages());
		return "selectStage";

	}

	@RequestMapping(value = "/admin/selectStage.html", method = RequestMethod.POST)
	public String selectStage(HttpServletRequest request, Integer plan_id,
			HttpSession session) {

		Integer stage_id = Integer.parseInt(request.getParameter("stage")
				.toString());

		Stage addStage = stageDao.getStage(stage_id);

		Plan newPlan = planDao.getPlan(plan_id);
		List<Stage> stageList = newPlan.getStages();
		stageList.add(addStage);
		newPlan.setStages(stageList);
		planDao.savePlan(newPlan);
		return "redirect:../plan.html?id=" + plan_id;

	}

	@RequestMapping(value = "/admin/addRunway.html", method = RequestMethod.GET)
	public String addRunway(ModelMap models, Integer plan_id,
			HttpSession session) {
		models.put("plan", planDao.getPlan(plan_id));
		models.put("runway", new Runway());
		return "addRunway";

	}

	@RequestMapping(value = "/admin/addRunway.html", method = RequestMethod.POST)
	public String addRunway(@ModelAttribute Runway runway,
			HttpServletRequest request, HttpSession session) {

		Integer plan_id = Integer.parseInt(request.getParameter("plan_id")
				.toString());
		Runway newRunway = runwayDao.saveRunway(runway);
		Plan plan = planDao.getPlan(plan_id);
		List<Runway> list = plan.getRunways();
		list.add(newRunway);
		plan.setRunways(list);
		planDao.savePlan(plan);
		return "redirect:../plan.html?id=" + plan_id;

	}

	@RequestMapping(value = "/admin/addStage.html", method = RequestMethod.GET)
	public String addStage(ModelMap models, Integer plan_id, HttpSession session) {
		models.put("plan", planDao.getPlan(plan_id));
		models.put("stage", new Stage());
		return "addStage";
	}

	@RequestMapping(value = "/admin/addStage.html", method = RequestMethod.POST)
	public String addStage(@ModelAttribute Stage stage,
			HttpServletRequest request, HttpSession session) {

		Integer plan_id = Integer.parseInt(request.getParameter("plan_id")
				.toString());
		Stage newStage = stageDao.saveStage(stage);
		Plan plan = planDao.getPlan(plan_id);
		List<Stage> list = plan.getStages();
		list.add(newStage);
		plan.setStages(list);
		planDao.savePlan(plan);
		return "redirect:../plan.html?id=" + plan_id;
	}

	@RequestMapping(value = "/admin/addCheckpoint.html", method = RequestMethod.GET)
	public String addCheckpoint(ModelMap map, HttpSession session,
			Integer plan_id) {

		session.setAttribute("plan_id", plan_id);
		session.setAttribute("runways", planDao.getPlan(plan_id).getRunways());
		session.setAttribute("stages", planDao.getPlan(plan_id).getStages());
		return "addCheckpoint";
	}

	@RequestMapping(value = "/admin/addCheckpoint.html", method = RequestMethod.POST)
	public String addCheckpoint(HttpSession session, HttpServletRequest request) {

		Integer runway_id = Integer.parseInt(request.getParameter("runway")
				.toString());
		Integer stage_id = Integer.parseInt(request.getParameter("stage")
				.toString());
		Integer plan_id = Integer.parseInt(request.getParameter("plan_id")
				.toString());

		Checkpoint checkpoint = new Checkpoint();
		checkpoint
				.setDescription(request.getParameter("checkpoint").toString());
		Checkpoint newCheck = checkpointDao.saveCheckpoint(checkpoint);

		Cell existCell = cellDao.isExistCell(runway_id, stage_id, plan_id);
		if (existCell == null) {

			Cell newCell = new Cell();

			newCell.setPlan(planDao.getPlan(plan_id));
			newCell.setRunway(runwayDao.getRunway(runway_id));
			newCell.setStage(stageDao.getStage(stage_id));

			List<Checkpoint> chklist = new ArrayList<Checkpoint>();
			newCheck.setOrderId(0);
			newCheck.setCell(newCell);
			chklist.add(newCheck);
			newCell.setCheckpoints(chklist);
			cellDao.saveCell(newCell);

		} else {
			List<Checkpoint> chklist = existCell.getCheckpoints();
			newCheck.setOrderId(chklist.size());
			newCheck.setCell(existCell);
			chklist.add(newCheck);
			existCell.setCheckpoints(chklist);
			cellDao.saveCell(existCell);
		}

		return "redirect:../plan.html?id=" + session.getAttribute("plan_id");
	}

	@RequestMapping(value = "/admin/editCheckpoint.html", method = RequestMethod.GET)
	public String editCheckpoint(ModelMap map, HttpSession session,
			@RequestParam Integer chk_id, @RequestParam Integer cell_id) {

		Cell cell = cellDao.getCell(cell_id);
		session.setAttribute("defaultStage", cell.getStage());
		session.setAttribute("defaultRunway", cell.getRunway());

		Checkpoint currentCheckpoint = checkpointDao.getCheckpoint(chk_id);
		session.setAttribute("checkpoint", currentCheckpoint);
		session.setAttribute("stages", cell.getPlan().getStages());
		session.setAttribute("runways", cell.getPlan().getRunways());
		session.setAttribute("cell_id", cell.getId());
		map.put("cell", cell);
		return "editCheckpoint";

	}

	@RequestMapping(value = "/admin/editCheckpoint.html", method = RequestMethod.POST)
	public String editCheckpoint(HttpServletRequest request, HttpSession session) {

		int checkpoint_id = Integer.parseInt(request.getParameter("chk_id")
				.toString());
		int stage_id = Integer.parseInt(request.getParameter("newStage")
				.toString());
		int runway_id = Integer.parseInt(request.getParameter("newRunway")
				.toString());
		int cell_id = Integer.parseInt(request.getParameter("cell_id")
				.toString());

		Cell currentCell = cellDao.getCell(cell_id);
		int plan_id = currentCell.getPlan().getId();

		Plan currentPlan = planDao.getPlan(plan_id);

		Checkpoint currentCheckpoint = checkpointDao
				.getCheckpoint(checkpoint_id);
		currentCheckpoint.setDescription(request.getParameter("newCheckpoint"));

		checkpointDao.saveCheckpoint(currentCheckpoint);

		List<Cell> listcell = currentPlan.getCells();
		for (int i = 0; i < listcell.size(); i++) {
			if (listcell.get(i).getId() == cell_id) {
				listcell.remove(i);
				break;
			}
		}

		currentPlan.setCells(listcell);
		planDao.savePlan(currentPlan);

		List<Checkpoint> checkpoints = currentCell.getCheckpoints();

		for (int i = 0; i < checkpoints.size(); i++) {
			if (checkpoints.get(i).getId() == checkpoint_id) {
				checkpoints.remove(i);
				break;

			}
		}

		currentCell.setCheckpoints(checkpoints);
		cellDao.saveCell(currentCell);

		listcell.add(currentCell);

		currentPlan.setCells(listcell);
		planDao.savePlan(currentPlan);

		Cell existCell = cellDao.isExistCell(runway_id, stage_id, plan_id);

		if (existCell == null) {
			System.out.println("null");

			Cell newCell = new Cell();
			
			newCell.setPlan(planDao.getPlan(plan_id));
			newCell.setRunway(runwayDao.getRunway(runway_id));
			newCell.setStage(stageDao.getStage(stage_id));

			List<Checkpoint> chklist = new ArrayList<Checkpoint>();
			currentCheckpoint.setOrderId(0);
			currentCheckpoint.setCell(newCell);
			
			chklist.add(currentCheckpoint);
			newCell.setCheckpoints(chklist);

			cellDao.saveCell(newCell);

		} else {
			List<Checkpoint> chklist = existCell.getCheckpoints();
			currentCheckpoint.setOrderId(chklist.size());
			currentCheckpoint.setCell(existCell);
			
			chklist.add(currentCheckpoint);
			existCell.setCheckpoints(chklist);
			cellDao.saveCell(existCell);
		}
		return "redirect:../plan.html?id=" + plan_id;
	}

	@RequestMapping("/admin/setActive.html")
	public String setActive(@RequestParam Integer id, ModelMap models,
			HttpSession session) {

		Plan currentPlan = planDao.getPlan(id);
		Department dept = currentPlan.getDepartment();
		dept.setCurrentPlan(currentPlan);
		departmentDao.saveDepartment(dept);
		return "redirect:../department.html?id=" + dept.getId();
	}

	@RequestMapping("/admin/publishPlan.html")
	public String publishPlan(@RequestParam Integer id, ModelMap models,
			HttpSession session) {

		Plan currentPlan = planDao.getPlan(id);
		Date date = new Date();
		currentPlan.setPublishedDate(date);
		planDao.savePlan(currentPlan);
		return "redirect:../department.html?id="
				+ currentPlan.getDepartment().getId();
	}
}
