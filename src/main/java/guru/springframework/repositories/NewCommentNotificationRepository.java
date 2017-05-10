package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.CommentNotification;





@Repository
public interface NewCommentNotificationRepository extends JpaRepository<CommentNotification, Long> {
	
}
