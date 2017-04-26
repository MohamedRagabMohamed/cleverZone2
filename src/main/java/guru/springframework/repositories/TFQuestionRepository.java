package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.TF_Question;

/**
 * The Interface TFQuestionRepository.
 */
@Repository
public interface TFQuestionRepository extends JpaRepository<TF_Question, Long> {

}
