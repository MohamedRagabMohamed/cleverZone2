package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 */
@Entity
@IdClass(ScorePK.class)
public class Score {


	
	/** The game. */
	@Id
	@ManyToOne
	@JoinColumn(name = "gameID" ,referencedColumnName = "id")
    private Game game;
	
	/** The user. */
	@Id
    @ManyToOne
    @JoinColumn(name = "userID",referencedColumnName = "id")
    private User user;
	
	/** The score value. */
	private long scoreValue;
	
	/**
	 * Instantiates a new score.
	 */
	public Score(){
		
	}
	
	/**
	 * Instantiates a new score.
	 *
	 * @param game the game
	 */
	public Score(Game game ) {
		this.game = game;
		
	}
	
	/**
	 * Instantiates a new score.
	 *
	 * @param game the game
	 * @param user the user
	 */
	public Score(Game game , User user) {
		this.game = game;
		this.user = user;
	}
	
	/**
	 * Instantiates a new score.
	 *
	 * @param game the game
	 * @param user the user
	 * @param score the score
	 */
	public Score(Game game , User user , int score) {
		this.game = game;
		this.user = user;
		this.scoreValue = score;
	}
	
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Long getGame() {
		return game.getId();
	}

	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@JsonIgnore
	public User getUser() {
		return user;
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
	 * Gets the score value.
	 *
	 * @return the score value
	 */
	public long getScoreValue() {
		return scoreValue;
	}

	/**
	 * Sets the score value.
	 *
	 * @param scoreValue the new score value
	 */
	public void setScoreValue(long scoreValue) {
		this.scoreValue = scoreValue;
	}
	
	
}
