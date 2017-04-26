package guru.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.domain.Course;



// TODO: Auto-generated Javadoc
/**
 * The Interface CourseRepository.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	/**
	 * Find byname.
	 *
	 * @param name the name
	 * @return the course
	 */
	Course findByname(String name);

}
