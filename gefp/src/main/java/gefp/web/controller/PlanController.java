package gefp.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gefp.model.Cell;
import gefp.model.Checkpoint;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.dao.CellDao;
import gefp.model.dao.CheckpointDao;
import gefp.model.dao.PlanDao;
import gefp.model.dao.RunwayDao;
import gefp.model.dao.StageDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlanController {

	@Autowired
	private PlanDao planDao;
	
	@Autowired
	private RunwayDao runwayDao;
	
	@Autowired
	private StageDao stageDao;
	
	@Autowired
	private CheckpointDao checkpointDao;
	
	@Autowired
	private CellDao cellDao;
	

	@RequestMapping(value = "/admin/editRunway.html",method = RequestMethod.GET)
	public String editRunway(@RequestParam Integer id,@RequestParam Integer plan_id,ModelMap models,HttpSession session)
	{
		models.put("plan_id", plan_id);
		models.put("runway", runwayDao.getRunway(id));
		return "editRunway";
	}
	
	@RequestMapping(value = "/admin/editRunway.html",method = RequestMethod.POST)
	public String editRunway(@ModelAttribute Runway runway,@RequestParam Integer plan_id)
	{
		runwayDao.saveRunway(runway);
		return "redirect:../plan.html?id=" + plan_id;
	}
	
	@RequestMapping(value = "/admin/editStage.html",method = RequestMethod.GET)
	public String editStage(@RequestParam Integer id,@RequestParam Integer plan_id,ModelMap models,HttpSession session)
	{
		models.put("plan_id", plan_id);
		models.put("stage", stageDao.getStage(id));
		return "editStage";
	}
	
	@RequestMapping(value = "/admin/editStage.html",method = RequestMethod.POST)
	public String editStage(@ModelAttribute Stage stage,@RequestParam Integer plan_id)
	{
		stageDao.saveStage(stage);
		return "redirect:../plan.html?id=" + plan_id;
	}
	
	@RequestMapping(value = "/admin/removeCheckpoint.html")
	public String removeCheckpoint(@RequestParam Integer chk_id,@RequestParam Integer cell_id)
	{
		Cell currentCell = cellDao.getCell(chk_id);
		Checkpoint checkpoint = checkpointDao.getCheckpoint(chk_id);
		List<Checkpoint> listCheckpoints = currentCell.getCheckpoints();
		for(Checkpoint c : currentCell.getCheckpoints())
		{
			if(c.getId() == checkpoint.getId())
			{
				listCheckpoints.remove(c);
			}
		}
		
		currentCell.setCheckpoints(listCheckpoints);
		cellDao.saveCell(currentCell);
		
		return "redirect:../plan.html?id=" + currentCell.getPlan().getId();
	}
	
	@RequestMapping(value = "/admin/reorderCheckpoint.html")
	public String reorderCheckpoint(@RequestParam Integer checkpointId,@RequestParam Integer newIndex,HttpServletRequest request)
	{
		Checkpoint checkpoint = checkpointDao.getCheckpoint(checkpointId);
		int oldIndex = checkpoint.getOrderId();
		Cell cell = cellDao.getCell(checkpoint.getCell().getId());
		List<Checkpoint> checkpointList = cell.getCheckpoints();
		checkpoint.setOrderId(newIndex);
		
		if(newIndex < oldIndex)
		{
			for(Checkpoint c: checkpointList)
			{
				if(c.getOrderId() >= newIndex && c.getId()!=checkpoint.getId() && c.getOrderId() <= oldIndex)
				{
					c.setOrderId(c.getOrderId()+1);
				}
			}
		}
		else
		{
			for(Checkpoint c: checkpointList)
			{
				if(c.getOrderId() <= newIndex && c.getId()!=checkpoint.getId() && c.getOrderId() >= oldIndex)
				{
					c.setOrderId(c.getOrderId()-1);
				}
			}
		}
		cell.setCheckpoints(checkpointList);
		cellDao.saveCell(cell);
	
		return "redirect:../plan.html?id=" + cell.getPlan().getId();
	}
	
}
