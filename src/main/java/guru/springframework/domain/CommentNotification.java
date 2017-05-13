package guru.springframework.domain;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class CommentNotification.
 */
@Entity
public class CommentNotification extends Notification {
	
	/** The commenteduser name. */
	private String theCommenteduserName;
	
	/**
	 * Instantiates a new comment notification.
	 */
	public CommentNotification(){
		super();
		theCommenteduserName = null;
	}
	
	/**
	 * Instantiates a new comment notification.
	 *
	 * @param user the user
	 * @param commenter the commenter
	 * @param gameId the game id
	 */
	public CommentNotification(User user , String commenter,Long gameId) {
		super();
		this.setUser(user);
		this.setGameId(gameId);
		this.setType("COMMENT");
		this.theCommenteduserName = commenter;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return theCommenteduserName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.theCommenteduserName = userName;
	} 
	

}
