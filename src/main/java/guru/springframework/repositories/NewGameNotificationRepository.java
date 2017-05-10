package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.NewGameNotification;





@Repository
public interface NewGameNotificationRepository extends JpaRepository<NewGameNotification, Long> {
	
}
