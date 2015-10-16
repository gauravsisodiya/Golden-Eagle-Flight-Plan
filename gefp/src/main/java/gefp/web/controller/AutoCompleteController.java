package gefp.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gefp.model.User;
import gefp.model.dao.UserDao;

@Controller
public class AutoCompleteController {

	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "advisor/autocomplete.html")
    public String users( @RequestParam String term, HttpServletResponse response )
        throws JSONException, IOException
    {
		JSONArray jsonArray = new JSONArray();
        List<User> users = userDao.searchUsersByPrefix(term, 10);
        for( User user : users )
        {
        	if(user.hasRole("ROLE_STUDENT"))
        	{
        		System.out.println("user name>>"+user.getUsername());
        		Map<String, String> json = new HashMap<String, String>();
                json.put( "id", Integer.toString(user.getId()) );
                json.put( "value", user.getName() );
                json.put( "label", user.getCin() + " " + user.getName() );
                jsonArray.put( json );
        	}
        	
        }

        response.setContentType( "application/json" );
        jsonArray.write( response.getWriter() );
        return null;
    }
}
