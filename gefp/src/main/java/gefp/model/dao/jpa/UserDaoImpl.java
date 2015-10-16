package gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import gefp.model.User;
import gefp.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    
    @Override
    public User getUsername( String username)
    {
    	try
    	{
    		return entityManager.createQuery( "from User where username = '" +username+ "'" , User.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }
    
    @Override
    @PostAuthorize("principal.username == returnObject.username or hasRole('ROLE_ADVISOR')")
    public User getUserById( int id)
    {
    	try
    	{
    		return entityManager.createQuery( "from User where id = "+id , User.class )
                    .getSingleResult();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
    
    }

    @Override
    public List<User> getUsers()
    {
    	try
    	{
    		return entityManager.createQuery( "from User order by id", User.class )
    	            .getResultList();
    	}
    	catch(NoResultException e)
    	{
    		return null;
    	}
        
    }
    
    @Override
	@Transactional
	@PreAuthorize ("principal.username == #user.username or hasRole('ROLE_ADVISOR')")
	public User saveUser (User user)
	{
		return entityManager.merge(user);
	}

    @Override
    public List<User> searchUsersByPrefix( String term, int maxResults )
    {
    	term = term.toLowerCase();
        String query = "from User where cin like :term || '%' "
            + "or lower(username) like :term || '%' "
            + "or lower(firstName) like :term || '%' "
            + "or lower(lastName) like :term || '%' "
            + "or lower(firstName || ' ' || lastName) like :term || '%' "
            + "or lower(emailId) like :term || '%' "
            + "order by firstName asc";

        return entityManager.createQuery( query, User.class )
            .setParameter( "term", term )
            .setMaxResults( maxResults )
            .getResultList();
    }
    @Override
    public List<User> searchUsers( String term )
    {
        term = term.toLowerCase();
        String query = "from User where cin = :term or lower(username) = :term "
            + "or lower(firstName) = :term or lower(lastName) = :term "
            + "or lower(firstName || ' ' || lastName) = :term "
            + "or lower(emailId) = :term order by firstName asc";

        return entityManager.createQuery( query, User.class )
            .setParameter( "term", term )
            .getResultList();
    }
   
}