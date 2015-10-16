package gefp.model.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration( locations = "classpath:applicationContext.xml" )
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void isUserJdoe1()
	{
		assert userDao.getUsername("jdoe1") != null;
	}
	
	@Test
	public void isUserJdoe2()
	{
		assert userDao.getUsername("jdoe2") != null;
	}
	
	/*@Test
	public void oneCheckpoint()
	{
		assert userDao.getUsername("jdoe1").getPlan().getCheckpoints().containsAll((userDao.getUsername("jdoe1").getCheckpoints()));
		assert userDao.getUsername("jdoe1").getCheckpoints().size() == 1;
	}
	public void allCheckpoint()
	{
		assert userDao.getUsername("jdoe2").getPlan().getCheckpoints().containsAll((userDao.getUsername("jdoe2").getCheckpoints()));
	}*/
}
