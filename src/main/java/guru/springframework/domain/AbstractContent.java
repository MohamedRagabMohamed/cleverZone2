package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;





// TODO: Auto-generated Javadoc
/**
 * The Class AbstractContent.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractContent {
	
	/** The id. */
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	 
	/** The course. */
	@JsonIgnore
	@ManyToOne
	private Course course ;
	//for angular it is the only way i found to know what is the type for this content so the frontend can route
	//and decide to which game page to will go 
	/** The Type. */
	private String Type;
	 
	
	/**
	 * Instantiates a new abstract content.
	 */
	public AbstractContent() {
		id = null;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Gets the course.
	 *
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	
	/**
	 * Sets the course.
	 *
	 * @param course the new course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		Type = type;
	}
	
}
