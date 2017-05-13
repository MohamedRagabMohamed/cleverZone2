package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Game extends AbstractContent {
	
	/** The name. */
	//write the common things between games
	@NotNull
	@NotEmpty
	protected String name;
	
	/** The descption. */
	@NotNull
	@NotEmpty
	private String descption;
	

	/** The Cancled. */
	private boolean Cancled;
	
	/** The image src. */
	@NotNull
	@NotEmpty
	private String imageSrc;
	
	/** The total time. */
	@NotNull
	private int totalTime;
	


	/** The score. */
	@OneToMany( mappedBy = "game",targetEntity = Score.class,cascade = {CascadeType.MERGE})
    private List<Score> score = new ArrayList<Score>();
	
	/** The Comments. */
	@OneToMany( mappedBy="game",targetEntity = Comment.class)
    private List<Comment> Comments = new ArrayList<Comment>();	
	
	/** The Collaborators. */
	@ManyToMany(mappedBy = "GamesCollaboratoredIn")
	private List<User> Collaborators = new ArrayList<User>();
	
	/**
	 * Gets the collaborators.
	 *
	 * @return the collaborators
	 */
	@JsonIgnore
	public List<User> getCollaborators() {
		return Collaborators;
	}
	
	public List<Long> getcollaborators() {
		List<Long> collaborators = new ArrayList<Long>();
		for(int i=0;i<Collaborators.size();i++){
			collaborators.add(Collaborators.get(i).getId());
		}
		return collaborators;
	}

	/**
	 * Sets the collaborators.
	 *
	 * @param collaborators the new collaborators
	 */
	public void setCollaborators(List<User> collaborators) {
		Collaborators = collaborators;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return Comments;
	}
	
	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 */
	public void addComment(Comment comment) {
		Comments.add(comment);
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
		Comments = comments;
	}
	

	/**
	 * Instantiates a new game.
	 */
	public Game(){
		super();
		score = new ArrayList<Score>();
		Comments = new ArrayList<Comment>();
		Collaborators = new ArrayList<User>();
		name = descption = imageSrc = null;
		totalTime = 0;
		Cancled = false;
	}
	
	/**
	 * Checks if is cancled.
	 *
	 * @return true, if is cancled
	 */
	public boolean isCancled() {
		return Cancled;
	}

	/**
	 * Sets the cancled.
	 *
	 * @param isCancled the new cancled
	 */
	public void setCancled(boolean isCancled) {
		this.Cancled = isCancled;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the descption.
	 *
	 * @return the descption
	 */
	public String getdescption() {
		return descption;
	}
	
	/**
	 * Sets the descption.
	 *
	 * @param desc the new descption
	 */
	public void setdescption(String desc) {
		this.descption = desc;
	}
	
	/**
	 * Gets the image src.
	 *
	 * @return the image src
	 */
	public String getImageSrc() {
		return imageSrc;
	}
	
	/**
	 * Sets the image src.
	 *
	 * @param imageSrc the new image src
	 */
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
	/**
	 * Gets the total time.
	 *
	 * @return the total time
	 */
	public int getTotalTime() {
		return totalTime;
	}

	/**
	 * Adds the total time.
	 *
	 * @param totalTime the total time
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	/**
	 * Adds the collaborator.
	 *
	 * @param user the user
	 */
	public void addCollaborator(User user) {
		Collaborators.add(user);
	}
	

}
