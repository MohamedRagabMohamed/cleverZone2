package guru.springframework.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Course.
 */
@Entity

public class Course {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	/** The name. */
	@NotNull
	@NotEmpty
	private String name;
	
	/** The descption. */
	@NotEmpty
	@NotNull
	String descption;
	
	/** The image src. */
	@NotEmpty
	@NotNull
	String imageSrc;
	
	/** The Contents. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course", targetEntity = AbstractContent.class)
	private List<AbstractContent> Contents;
	
	/** The Teacher. */
	@ManyToOne
	User Teacher;

	/** The users. */
	@ManyToMany(mappedBy = "CoursesRegistedin")
	List<User> users;

	/**
	 * Instantiates a new course.
	 */
	public Course() {
		id = null;
		Contents = new ArrayList<>();
		users = new ArrayList<User>();
	}

	/**
	 * Instantiates a new course.
	 *
	 * @param name the name
	 * @param desc the desc
	 * @param imageSrc the image src
	 */
	public Course(String name, String desc, String imageSrc) {
		this();
		this.descption = desc;
		this.name = name;
		this.imageSrc = imageSrc;
	}

	/**
	 * Gets the teacher.
	 *
	 * @return the teacher
	 */
	@JsonIgnore
	public User getTeacher() {
		return Teacher;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	@JsonIgnore
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Sets the teacher.
	 *
	 * @param teacher the new teacher
	 */
	public void setTeacher(User teacher) {
		Teacher = teacher;
	}

	/**
	 * Gets the contents.
	 *
	 * @return the contents
	 */
	public List<AbstractContent> getContents() {
		return Contents;
	}

	/**
	 * Adds the contents.
	 *
	 * @param content the content
	 */
	public void addContents(AbstractContent content) {
		content.setCourse(this);
		Contents.add(content);
	}

	/**
	 * Adds the student.
	 *
	 * @param user the user
	 */
	public void addStudent(User user) {
		users.add(user);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the descption.
	 *
	 * @return the descption
	 */
	public String getDescption() {
		return descption;
	}

	/**
	 * Sets the descption.
	 *
	 * @param descption the new descption
	 */
	public void setDescption(String descption) {
		this.descption = descption;
	}

	/**
	 * Gets the image src.
	 *
	 * @return the image src
	 */
	public String getImageSrc() {
		return imageSrc;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the image src.
	 *
	 * @param imageSrc the new image src
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

}
