package guru.springframework.domain;

import javax.persistence.Entity;

@Entity
public class NewGameNotification extends Notification {

	private String CourseName;
	
	public NewGameNotification(){
		super();
		CourseName = null;
	}
	public NewGameNotification(User user , String CourseName,Long gameId) {
		super();
		this.setUser(user);
		this.setGameId(gameId);
		this.setType("GAME");
		this.CourseName = CourseName;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	
	
}
