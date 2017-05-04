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

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
//@MappedSuperclass
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
	

	private boolean Cancled;
	
	/** The image src. */
	@NotNull
	@NotEmpty
	private String imageSrc;
	
	/** The total time. */
	@NotNull
	private int totalTime;
	


	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "game",targetEntity = Score.class)
    private List<Score> score = new ArrayList<Score>();
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy="game",targetEntity = Comment.class)
    private List<Comment> Comments = new ArrayList<Comment>();	
	
	@ManyToMany(mappedBy = "GamesCollaboratoredIn")
	private List<User> Collaborators = new ArrayList<User>();
	
	public List<User> getCollaborators() {
		return Collaborators;
	}

	public void setCollaborators(List<User> collaborators) {
		Collaborators = collaborators;
	}

	public List<Comment> getComments() {
		return Comments;
	}
	public void addComment(Comment comment) {
		Comments.add(comment);
	}
	public void setComments(List<Comment> comments) {
		Comments = comments;
	}
	

	/**
	 * Instantiates a new game.
	 */
	public Game(){
		super();
		score = new ArrayList<Score>();
		name = descption = imageSrc = null;
		totalTime = 0;
		Cancled = false;
	}
	
	public boolean isCancled() {
		return Cancled;
	}

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
	
	public void addCollaborator(User user) {
		Collaborators.add(user);
	}
	

}
