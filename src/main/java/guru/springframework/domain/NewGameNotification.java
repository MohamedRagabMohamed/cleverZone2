package guru.springframework.domain;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class NewGameNotification.
 */
@Entity
public class NewGameNotification extends Notification {

	/** The Course name. */
	private String CourseName;
	
	/**
	 * Instantiates a new new game notification.
	 */
	public NewGameNotification(){
		super();
		CourseName = null;
	}
	
	/**
	 * Instantiates a new new game notification.
	 *
	 * @param user the user
	 * @param CourseName the course name
	 * @param gameId the game id
	 */
	public NewGameNotification(User user , String CourseName,Long gameId) {
		super();
		this.setUser(user);
		this.setGameId(gameId);
		this.setType("GAME");
		this.CourseName = CourseName;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return CourseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	
	
}
