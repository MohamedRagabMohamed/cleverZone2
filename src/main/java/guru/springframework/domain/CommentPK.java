package guru.springframework.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CommentPK implements Serializable {

    private Long game;

    //@Id 
    //@GeneratedValue 
    //long id;
    
    private Long user;


	public Long getGame() {
		return game;
	}


	public void setGame(Long game) {
		this.game = game;
	}


	public Long getUser() {
		return user;
	}


	public void setUser(Long user) {
		this.user = user;
	}
}
