package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class TF_Game extends Game {
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="TF_TheGame",targetEntity = TF_Question.class)
	private List<TF_Question> Questions;
	
	public TF_Game(){
		super();
		Questions = new ArrayList<>();
	}
	public TF_Game(String name , String desc , String imageSrc){
		this();
		this.setName(name);
		this.setdescption(desc);
		this.setImageSrc(imageSrc);
	}
	

	public List<TF_Question> getQuestions() {
		return Questions;
	}
	public void addQuestion(TF_Question question) {
		this.addTotalTime(question.getTime());
		question.setTF_TheGame(this);
		Questions.add(question);
	}
	
}
