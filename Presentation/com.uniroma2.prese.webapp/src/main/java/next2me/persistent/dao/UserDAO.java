package next2me.persistent.dao;

import next2me.persistent.entity.User;

public interface UserDAO {
	public void addUser(User user);
	public User getUser(String email);
	public User getUserName(String nome);
}
