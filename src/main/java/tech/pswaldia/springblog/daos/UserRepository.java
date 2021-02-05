package tech.pswaldia.springblog.daos;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.pswaldia.springblog.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
