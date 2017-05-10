package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Notification {
	
	/** The id. */
	@Id 
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	private User user ;
	
	private Long gameId;
	
	private String Type;


	public Notification() {
		id = null;
		setType(null);
	}
	public Notification(User user, String type) {
		super();
		this.user = user;
		setType(type);
	}
	public Long getUser(){
		return user.getId();
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
}
