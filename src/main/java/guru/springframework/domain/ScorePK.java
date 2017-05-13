package guru.springframework.domain;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class ScorePK.
 */
public class ScorePK implements Serializable  {

	
    /** The game. */
    private Long game;

    
    /** The user. */
    private Long user;


	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Long getGame() {
		return game;
	}


	/**
	 * Sets the game.
	 *
	 * @param game the new game
	 */
	public void setGame(Long game) {
		this.game = game;
	}


	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public Long getUser() {
		return user;
	}


	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(Long user) {
		this.user = user;
	}
	
	
}
