package gefp.model.dao.jpa;

import gefp.model.Department;
import gefp.model.dao.DepartmentDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Department getDepartment(int id)
	{
		try
    	{
    		return entityManager.createQuery( "from Department where id = " +id , Department.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
	}
	
	@Override
	public List<Department> getDepartments()
	{
		try
    	{
    		return entityManager.createQuery( "from Department order by id", Department.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
				
	}
	
	@Override
	@Transactional
	public Department saveDepartment(Department department)
	{
		return entityManager.merge(department);
	}
}
