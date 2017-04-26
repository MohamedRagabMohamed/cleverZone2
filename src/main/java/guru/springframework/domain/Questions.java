package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

// TODO: Auto-generated Javadoc
/**
 * The Class Questions.
 */
@Entity
public abstract class Questions {
	
	/** The Question. */
	@NotNull
	@NotEmpty
	private String Question;
	
	/** The Answer. */
	@NotNull
	@NotEmpty
	private String Answer;
	
	/** The Time. */
	@NotNull
	private int Time;
	
	
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	public final Long id ; 
	
	
	/**
	 * Instantiates a new questions.
	 */
	public Questions(){
		id = null;
		Question = Answer = null;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public String getQuestion() {
		return Question;
	}
	
	/**
	 * Sets the question.
	 *
	 * @param question the new question
	 */
	public void setQuestion(String question) {
		Question = question;
	}
	
	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	public String getAnswer() {
		return Answer;
	}
	
	/**
	 * Sets the answer.
	 *
	 * @param answer the new answer
	 */
	public void setAnswer(String answer) {
		Answer = answer;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime() {
		return Time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time) {
		Time = time;
	}
	
	

}
