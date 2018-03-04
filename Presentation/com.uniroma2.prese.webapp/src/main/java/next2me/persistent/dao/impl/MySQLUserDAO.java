package next2me.persistent.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import next2me.persistent.dao.UserDAO;
import next2me.persistent.entity.User;
import next2me.utils.CryptPassword;


public class MySQLUserDAO extends SessionFactoryHibernate implements UserDAO{
	
	@Transactional
	public void addUser(User user) {
		user.setPassword(CryptPassword.cryptWithMD5(user.getPassword()));
        getSession().save(user);
        getSession().flush();        	
	}

	public User getUser(String email) {
		return (User) getSession().createQuery("from User u where u.email = :email")
				.setParameter("email", email).uniqueResult();
	}
	
	public User getUserName(String nome) {
		return (User) getSession().createQuery("from User u where u.nome = :nome")
				.setParameter("nome", nome).uniqueResult();
	}
	
}
