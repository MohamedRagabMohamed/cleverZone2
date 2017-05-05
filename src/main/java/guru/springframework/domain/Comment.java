package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(CommentPK.class)
public class Comment {
	@Id
	@ManyToOne
	@JoinColumn(name = "gameID" ,referencedColumnName = "id")
    private Game game;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "userID",referencedColumnName = "id")
    private User user;
	
	private String Text;

	public Comment() {
		super();
		this.game = null;
		this.user = null;
		Text = null;
	}
	public Comment(Game game, User user, String text) {
		super();
		this.game = game;
		this.user = user;
		Text = text;
	}
	public Comment(Game game, String text) {
		super();
		this.game = game;
		this.Text = text;
	}
	@JsonIgnore
	public Game getGame() {
		return game;
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

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}


	
}
