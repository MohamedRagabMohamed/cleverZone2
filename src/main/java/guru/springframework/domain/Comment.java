package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Comment.
 */
@Entity
@IdClass(CommentPK.class)
public class Comment {

	@Id
	@ManyToOne
	@JoinColumn(name = "gameID" ,referencedColumnName = "id")
    private Game game;
	
	/** The user. */
	@Id
    @ManyToOne
    @JoinColumn(name = "userID",referencedColumnName = "id")
    private User user;
	
	/** The Text. */
	private String Text;

	/**
	 * Instantiates a new comment.
	 */
	public Comment() {
		super();
		this.game = null;
		this.user = null;
		Text = null;
	}
	
	/**
	 * Instantiates a new comment.
	 *
	 * @param game the game
	 * @param user the user
	 * @param text the text
	 */
	public Comment(Game game, User user, String text) {
		this();
		this.game = game;
		this.user = user;
		Text = text;
	}
	
	/**
	 * Instantiates a new comment.
	 *
	 * @param game the game
	 * @param text the text
	 */
	public Comment(Game game, String text) {
		this();
		this.game = game;
		this.Text = text;
	}
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	@JsonIgnore
	public Game getGame() {
		return game;
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
	 * Gets the comment author.
	 *
	 * @return the comment author
	 */
	public String getCommentAuthor() {
		return user.getUserName();
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
//	public Long getUser(){
//		return user;
//	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return Text;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		Text = text;
	}


	
}
