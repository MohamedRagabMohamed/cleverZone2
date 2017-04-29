package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;




// TODO: Auto-generated Javadoc
/**
 * The Class TF_Question.
 */
@Entity
public class TF_Question extends Questions{
	
	/** The T F the game. */
	@JsonIgnore
	@ManyToOne
	private TF_Game TF_TheGame;
	
	/**
	 * Gets the t F the game.
	 *
	 * @return the t F the game
	 */
	@JsonIgnore
	public TF_Game getTF_TheGame() {
		return TF_TheGame;
	}
	
	/**
	 * Sets the t F the game.
	 *
	 * @param tF_TheGame the new t F the game
	 */
	public void setTF_TheGame(TF_Game tF_TheGame) {
		TF_TheGame = tF_TheGame;
	}
	
	/**
	 * Instantiates a new t F question.
	 */
	public TF_Question() {
		super();
	}
	
	/**
	 * Instantiates a new t F question.
	 *
	 * @param question the question
	 * @param answer the answer
	 * @param time the time
	 */
	public TF_Question(String question,String answer) {
		this();
		this.setQuestion(question);
		this.setAnswer(answer);
	}
	
	/**
	 * Instantiates a new t F question.
	 *
	 * @param question the question
	 * @param answer the answer
	 * @param game the game
	 */
	public TF_Question(String question,String answer,TF_Game game) {
		this();
		this.setQuestion(question);
		this.setAnswer(answer);
		TF_TheGame = game;
	}
}
