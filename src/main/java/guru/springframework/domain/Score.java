package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(ScorePK.class)
public class Score {

//	@Id
//	private Long gameID;
//
//	@Id    
//	private Long userID;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "gameID" ,referencedColumnName = "id")
    private Game game;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "userID",referencedColumnName = "id")
    private User user;
	
	private long scoreValue;
	
	public Score(){
		
	}
	public Score(Game game ) {
		this.game = game;
		
	}
	public Score(Game game , User user) {
		this.game = game;
		this.user = user;
	}
	
	public Score(Game game , User user , int score) {
		this.game = game;
		this.user = user;
		this.scoreValue = score;
	}
	
	
	public Long getGame() {
		return game.getId();
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(long scoreValue) {
		this.scoreValue = scoreValue;
	}
	
	
}
