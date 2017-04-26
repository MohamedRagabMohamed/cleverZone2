package guru.springframework.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.MCQ_Game;

// TODO: Auto-generated Javadoc
/**
 * The Interface MCQGameRepository.
 */
@Repository
public interface MCQGameRepository extends JpaRepository<MCQ_Game, Long> {
	
	/**
	 * Find byname.
	 *
	 * @param name the name
	 * @return the MC Q game
	 */
	MCQ_Game findByname(String name);

}
