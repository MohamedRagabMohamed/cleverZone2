package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.TF_Game;

@Repository
public interface TFGameRepository extends JpaRepository<TF_Game, Long> {
	TF_Game findByname(String name);

}