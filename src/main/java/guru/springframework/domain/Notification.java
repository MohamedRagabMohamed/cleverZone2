package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notification {
	
	/** The id. */
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	/** The user. */
	@ManyToOne
	private User user ;
	
	/** The game id. */
	private Long gameId;
	
	/** The Type. */
	private String Type;


	/**
	 * Instantiates a new notification.
	 */
	public Notification() {
		id = null;
		setType(null);
	}
	
	/**
	 * Instantiates a new notification.
	 *
	 * @param user the user
	 * @param type the type
	 */
	public Notification(User user, String type) {
		super();
		this.user = user;
		setType(type);
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Long getUser(){
		return user.getId();
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
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public Long getGameId() {
		return gameId;
	}
	
	/**
	 * Sets the game id.
	 *
	 * @param gameId the new game id
	 */
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
}
