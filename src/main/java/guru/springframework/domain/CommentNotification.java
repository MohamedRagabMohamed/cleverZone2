package guru.springframework.domain;

import javax.persistence.Entity;

@Entity
public class CommentNotification extends Notification {
	
	private String theCommenteduserName;
	
	public CommentNotification(){
		super();
		theCommenteduserName = null;
	}
	
	public CommentNotification(User user , String commenter,Long gameId) {
		super();
		this.setUser(user);
		this.setGameId(gameId);
		this.setType("COMMENT");
		this.theCommenteduserName = commenter;
	}
	public String getUserName() {
		return theCommenteduserName;
	}
	public void setUserName(String userName) {
		this.theCommenteduserName = userName;
	} 
	

}
