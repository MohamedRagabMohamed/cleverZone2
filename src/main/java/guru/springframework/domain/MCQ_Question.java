package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;




// TODO: Auto-generated Javadoc
/**
 * The Class MCQ_Question.
 */
@Entity
public class MCQ_Question extends Questions {
	
	
	/** The MC Q the game. */
	@ManyToOne
	@JsonIgnore
	MCQ_Game MCQ_TheGame;
	
	
	/** The Choices. */
	private String Choices[];
	
	/**
	 * Instantiates a new MC Q question.
	 */
	public MCQ_Question(){
		super();
	}
	
	/**
	 * Instantiates a new MC Q question.
	 *
	 * @param Question the question
	 * @param answer the answer
	 * @param Choices the choices
	 * @param time the time
	 */
	public MCQ_Question( String Question ,String answer , String [] Choices,int time){
		this();
		this.setAnswer(answer);
		this.setQuestion(Question);
		this.Choices = Choices;
		this.setTime(time);
	}
	
	/**
	 * Gets the MC Q the game.
	 *
	 * @return the MC Q the game
	 */
	@JsonIgnore
	public MCQ_Game getMCQ_TheGame() {
		return MCQ_TheGame;
	}
	
	/**
	 * Sets the MC Q the game.
	 *
	 * @param mCQ_TheGame the new MC Q the game
	 */
	public void setMCQ_TheGame(MCQ_Game mCQ_TheGame) {
		MCQ_TheGame = mCQ_TheGame;
	}
	
	/**
	 * Instantiates a new MC Q question.
	 *
	 * @param Question the question
	 * @param answer the answer
	 * @param Choices the choices
	 * @param game the game
	 */
	public MCQ_Question( String Question,String answer , String [] Choices,Game game){
		this();
		this.setAnswer(answer);
		this.setQuestion(Question);
		this.Choices = Choices;
		
		
	}
	
	/**
	 * Gets the choices.
	 *
	 * @return the choices
	 */
	public String [] getChoices() {
		return Choices;
	}
	
	/**
	 * Sets the choices.
	 *
	 * @param choices the new choices
	 */
	public void setChoices(String [] choices) {
		Choices = choices;
	}
	
	/**
	 * Checks if is valid.
	 *
	 * @return the boolean
	 */
	@JsonIgnore
	public Boolean isValid(){
			Boolean flag = false;
			for(int i=0;i<Choices.length;i++){
	    	   if(Choices[i].equals(this.getAnswer())){
	    		   flag = true;
	    	   }
	       }
			return flag;
	}
}
