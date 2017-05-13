package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
		this.id = null;
		this.game = null;
		this.user = null;
		Text = null;
	}
	public Comment(Game game, User user, String text) {
		this();
		this.game = game;
		this.user = user;
		Text = text;
	}

	public Comment(Game game, String text) {
		this();
		this.game = game;
		Text = text;
	}


	public void setGame(Game game) {
		this.game = game;
	}

	public void setUser(User user) {
		this.user = user;
	}
//	public Long getUser(){
//		return user;
//	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}


	
}
