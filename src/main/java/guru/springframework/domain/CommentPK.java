package guru.springframework.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class CommentPK implements Serializable {

	
    private Game game;
    
    private User user;



	public Long getId() {
		return id;
	}

}
