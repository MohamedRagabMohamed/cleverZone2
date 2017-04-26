package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.TF_Game;

// TODO: Auto-generated Javadoc
/**
 * The Interface TFGameRepository.
 */
@Repository
public interface TFGameRepository extends JpaRepository<TF_Game, Long> {
	
	/**
	 * Find byname.
	 *
	 * @param name the name
	 * @return the t F game
	 */
	TF_Game findByname(String name);

}