package com.corey.vidlib.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.corey.vidlib.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements UserDao {

	// Define field for entitymanager

	private EntityManager entityManager;

	// Set up constructor injection

	@Autowired
	public UserDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public User findByUserName(String theUserName) {
		// Get the current hibernate session

		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public User findByEmail(String theEmail) {
		// Get the current hibernate session

		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where email=:email", User.class);
		theQuery.setParameter("email", theEmail);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public User save(User theUser) {
		// Get the current hibernate session

		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);

		return theUser;
	}

	@Override
	public User findById(Long id) {

		// Get the current hibernate session

		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where id=:id", User.class);
		theQuery.setParameter("id", id);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;

	}

}
