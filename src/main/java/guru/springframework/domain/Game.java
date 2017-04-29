package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	
	/** The image src. */
	@NotNull
	@NotEmpty
	private String imageSrc;
	
	/** The total time. */
	@NotNull
	private int totalTime;
	
	@OneToMany(mappedBy = "game")
    private List<Score> score = new ArrayList<Score>();
	

	/**
	 * Instantiates a new game.
	 */
	public Game(){
		super();
		score = new ArrayList<Score>();
		name = descption = imageSrc = null;
		totalTime = 0;
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
	

}
