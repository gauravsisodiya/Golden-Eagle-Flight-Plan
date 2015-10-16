package gefp.model.dao.jpa;

import java.util.List;

import gefp.model.Plan;
import gefp.model.User;
import gefp.model.dao.PlanDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PlanDaoImpl implements PlanDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
    public Plan getPlanId()
    {
    	try
    	{
    		return entityManager.createQuery( "from Plan" , Plan.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
	
	@Override
	public Plan getPlan(int id)
	{
		try
    	{
    		return entityManager.createQuery( "from Plan where id = " +id , Plan.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
	}
	
	@Override
	public List<Plan> getPlans()
	{
		try
    	{
    		return entityManager.createQuery( "from Plan order by id", Plan.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
				
	}
	
	@Override
	@Transactional
	public Plan savePlan(Plan plan)
	{	
		System.out.println("impl");
		return entityManager.merge(plan);
	}
	
}
