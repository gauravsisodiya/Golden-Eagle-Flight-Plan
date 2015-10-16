package gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gefp.model.Runway;
import gefp.model.dao.RunwayDao;


@Repository
public class RunwayDaoImpl implements RunwayDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Runway> getRunways()
    {
    	try
    	{
    		return entityManager.createQuery( "from Runway order by id asc", Runway.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
        
    }
	
	@Override
    public Runway getRunway( int id)
    {
    	try
    	{
    		return entityManager.createQuery( "from Runway where id = "+id, Runway.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
	
	@Override
	@Transactional
	public Runway saveRunway(Runway runway)
	{
		return entityManager.merge(runway);
	}
	
	
}
