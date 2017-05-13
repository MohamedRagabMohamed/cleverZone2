package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class TF_Game.
 */
@Entity
public class TF_Game extends Game {
	
	/** The Questions. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy="TF_TheGame",targetEntity = TF_Question.class)
	private List<TF_Question> Questions;
	
	/**
	 * Instantiates a new t F game.
	 */
	public TF_Game(){
		super();
		Questions = new ArrayList<>();
	}
	
	/**
	 * Instantiates a new t F game.
	 *
	 * @param name the name
	 * @param desc the desc
	 * @param imageSrc the image src
	 * @param totalTime the total time
	 * @param cancled the cancled
	 */
	public TF_Game(String name , String desc , String imageSrc,int totalTime,boolean cancled){
		this();
		this.setCancled(cancled);
		this.setName(name);
		this.setdescption(desc);
		this.setImageSrc(imageSrc);
		this.setType("TF");
		this.setTotalTime(totalTime);
	}
	

	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public List<TF_Question> getQuestions() {
		return Questions;
	}
	
	/**
	 * Adds the question.
	 *
	 * @param question the question
	 */
	public void addQuestion(TF_Question question) {
		question.setTF_TheGame(this);
		Questions.add(question);
	}
	
}
