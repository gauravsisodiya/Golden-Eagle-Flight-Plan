package gefp.web.validator;

import gefp.model.User;
import gefp.model.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator{

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
		String pattern = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{4,}$";
		User user = (User)target;
	
		if(!user.getUsername().equals(user.getPassword()))
		{
			errors.rejectValue("username", "error.password.match");
		}
		if(!user.getUsername().matches(pattern))
		{
			errors.rejectValue("username", "error.password.format");
		}
		
	}

}
