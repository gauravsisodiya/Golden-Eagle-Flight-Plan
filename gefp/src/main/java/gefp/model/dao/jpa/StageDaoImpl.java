package gefp.model.dao.jpa;

import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.dao.StageDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StageDaoImpl implements StageDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Stage> getStages()
    {
    	try
    	{
    		return entityManager.createQuery( "from Stage order by id", Stage.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
        
    }
	
	@Override
    public Stage getStage( int id )
    {
    	try
    	{
    		return entityManager.createQuery( "from Stage where id = "+id, Stage.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
	
	@Override
	@Transactional
	public Stage saveStage(Stage stage)
	{
		return entityManager.merge(stage);
	}
}
