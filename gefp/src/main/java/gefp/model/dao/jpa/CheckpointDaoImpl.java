package gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gefp.model.Checkpoint;
import gefp.model.dao.CheckpointDao;

@Repository
public class CheckpointDaoImpl  implements CheckpointDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Checkpoint> getCheckpoints()
    {
    	try
    	{
    		return entityManager.createQuery( "from Checkpoint order by id", Checkpoint.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
        
    }
	
	@Override
    public Checkpoint getCheckpoint( int id)
    {
    	try
    	{
    		return entityManager.createQuery( "from Checkpoint where id = "+id, Checkpoint.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
	
	@Override
    public Checkpoint getCheckpointByOrder( int orderId,int cellId)
    {
    	try
    	{
    		return entityManager.createQuery( "from Checkpoint where orderid = "+orderId +"and cellid="+cellId, Checkpoint.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
	
	@Override
	@Transactional
	public Checkpoint saveCheckpoint (Checkpoint checkpoint)
	{
		return entityManager.merge(checkpoint);
	}
}
