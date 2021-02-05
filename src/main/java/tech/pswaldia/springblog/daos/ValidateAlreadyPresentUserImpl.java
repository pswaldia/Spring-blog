package tech.pswaldia.springblog.daos;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import tech.pswaldia.springblog.entites.User;

@Repository
public class ValidateAlreadyPresentUserImpl implements ValidateAlreadyPresentUser {

	@PersistenceContext
	EntityManager em;

	@Override
	public Optional<User> findByUserNameOrEmail(String username, String email) {
		TypedQuery<User> sqlQuery = em.createQuery("from User u where u.userName = ?1 or u.email = ?2", User.class);
		sqlQuery.setParameter(1, username);
		sqlQuery.setParameter(2, email);
		try {
			User user = sqlQuery.getSingleResult();
			return Optional.ofNullable(user);
		}catch (NoResultException e) {
			return null;
		}
	}

}
