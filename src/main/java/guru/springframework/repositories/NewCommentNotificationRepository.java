package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.CommentNotification;





/**
 * The Interface NewCommentNotificationRepository.
 */
@Repository
public interface NewCommentNotificationRepository extends JpaRepository<CommentNotification, Long> {
	
}
