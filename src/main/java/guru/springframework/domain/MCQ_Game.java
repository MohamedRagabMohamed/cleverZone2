package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;



// TODO: Auto-generated Javadoc
/**
 * The Class MCQ_Game.
 */
@Entity
public class MCQ_Game extends  Game{
	
	/** The Questions. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy="MCQ_TheGame",targetEntity = MCQ_Question.class)
	
	private List<MCQ_Question> Questions;
	
	

	/**
	 * Instantiates a new MC Q game.
	 */
	public MCQ_Game(){
		super();
		Questions = new ArrayList<>();
	}
	
	/**
	 * Instantiates a new MC Q game.
	 *
	 * @param name the name
	 * @param desc the desc
	 * @param imageSrc the image src
	 */
	public MCQ_Game(String name , String desc , String imageSrc ,int totalTime,boolean cancled){
		this();
		this.setCancled(cancled);
		this.setName(name);
		this.setdescption(desc);
		this.setImageSrc(imageSrc);
		this.setType("MCQ");
		this.setTotalTime(totalTime);
	}
	
	
	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public List<MCQ_Question> getQuestions() {
		return Questions;
	}
	
	/**
	 * Adds the question.
	 *
	 * @param question the question
	 */
	public void addQuestion(MCQ_Question question) {
		question.setMCQ_TheGame(this);
		Questions.add(question);
	}

	
}
