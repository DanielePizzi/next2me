package next2me.persistent.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import next2me.hibernate.config.HibernateUtil;


public class SessionFactoryHibernate {
	@Autowired
    private SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	protected Session getSession(){
		return sessionFactory.openSession();
	}
}
