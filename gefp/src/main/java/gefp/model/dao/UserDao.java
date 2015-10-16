package gefp.model.dao;

import java.util.List;

import gefp.model.User;

public interface UserDao {

    User getUsername(String username);
    
    List<User> getUsers();

	User getUserById(int id);

	User saveUser(User user);

	List<User> searchUsersByPrefix(String term, int maxResults);

	List<User> searchUsers(String term);

	

}