package gefp.model.dao;

import java.util.List;

import gefp.model.Plan;

public interface PlanDao {

	Plan getPlan(int id);

	List<Plan> getPlans();

	Plan savePlan(Plan plan);

	Plan getPlanId();

}
