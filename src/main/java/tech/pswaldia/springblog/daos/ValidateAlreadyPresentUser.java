package tech.pswaldia.springblog.daos;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tech.pswaldia.springblog.entites.User;

@Repository
public interface ValidateAlreadyPresentUser {
	Optional<User> findByUserNameOrEmail(String username, String email);
}
