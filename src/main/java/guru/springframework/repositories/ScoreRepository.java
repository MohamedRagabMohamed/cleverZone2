package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Course;
import guru.springframework.domain.Score;



/**
 * The Interface ScoreRepository.
 */
@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
	
}
