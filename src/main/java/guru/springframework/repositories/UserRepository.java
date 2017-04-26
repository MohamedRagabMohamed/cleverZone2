package guru.springframework.repositories;

import guru.springframework.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Find byuser name.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByuserName(String username);
}
