package gefp.model.dao.jpa;

import java.util.List;

import gefp.model.Cell;
import gefp.model.dao.CellDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CellDaoImpl implements CellDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cell isExistCell(Integer runway_id,Integer stage_id,Integer plan_id)
	{
		try
		{
			return entityManager.createQuery("from Cell where runway_id="+runway_id+" and stage_id="+stage_id+" and plan_id="+plan_id,Cell.class
					).getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	@Override
	public Cell getCell(Integer cell_id)
	{
		try
		{
			return entityManager.createQuery("from Cell where id="+cell_id,Cell.class
					).getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}

	
	@Override
	@Transactional
	public Cell saveCell(Cell cell)
	{
		return entityManager.merge(cell);
	}
}
