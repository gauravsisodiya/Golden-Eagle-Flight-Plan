package gefp.web.validator;

import gefp.model.User;
import gefp.model.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Autowired
    private UserDao userDao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		//System.out.println("user"+user.getUsername());
		if(!StringUtils.hasText(user.getUsername()))
		{
			System.out.println("uname");
			errors.rejectValue("username", "error.username.empty");
		}
		if(!StringUtils.hasText(user.getPassword()))
		{	
			System.out.println("pwd");
			errors.rejectValue("password", "error.password.empty");}
		
	}

	
}
