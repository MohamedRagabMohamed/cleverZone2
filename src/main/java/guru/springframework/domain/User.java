package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@Entity
public class User {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	
	/** The Constant PASSWORD_ENCODER. */
	public static final PasswordEncoder PASSWORD_ENCODER =  new BCryptPasswordEncoder();
	
	/** The user name. */
	@NotNull
	@NotEmpty
	private String userName;
	
	/** The first name. */
	@NotNull
	@NotEmpty
	private String firstName;
	
	/** The last name. */
	@NotNull
	@NotEmpty
	private String lastName;
	
	/** The password. */
	@NotNull
	@NotEmpty
	private String password;
	
	/** The Roles. */
	@NotNull
	@NotEmpty
	private String [] Roles;
	
	
	/** The Courses created. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Teacher",targetEntity = Course.class)
	private List<Course> CoursesCreated;
	
	
	/**  The Courses register. */
	@ManyToMany(cascade = {CascadeType.ALL} )
	private List<Course> CoursesRegistedin = new ArrayList<Course>();
	


	/** The Scores. */
	@OneToMany(mappedBy="user", cascade = {CascadeType.MERGE} )
    private List<Score> Scores = new ArrayList<Score>();
	
	/** The Comments. */
	@OneToMany( mappedBy="user", cascade = {CascadeType.MERGE} )
    private List<Comment> Comments = new ArrayList<Comment>();	


	/** The Games collaboratored in. */
	@ManyToMany( cascade = CascadeType.ALL )
	private List<Game> GamesCollaboratoredIn = new ArrayList<Game>();

	/** The Notifications. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", targetEntity = Notification.class)
	private List<Notification> Notifications;
	
	
	/**
	 * Instantiates a new user.
	 */
	User(){
		id = null;
		CoursesCreated = new ArrayList<>();
		CoursesRegistedin = new ArrayList<Course>();
		Scores = new ArrayList<Score>();
		Comments = new ArrayList<Comment>();
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param password the password
	 * @param roles the roles
	 */
	public User(String userName , String firstName, String lastName , String password , String [] roles){
		this();
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.Roles = roles;
		this.password = PASSWORD_ENCODER.encode(password);
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Comment> getComments() {
		return Comments;
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
	 * Adds the comment.
	 *
	 * @param comment the comment
	 */
	public void addComment(Comment comment) {
		comment.setUser(this);
		Comments.add(comment);
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public String[] getRoles() {
		return Roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(String[] roles) {
		Roles = roles;
	}
	
	/**
	 * Gets the games collaboratored in.
	 *
	 * @return the games collaboratored in
	 */
	public List<Game> getGamesCollaboratoredIn() {
		return GamesCollaboratoredIn;
	}

	/**
	 * Sets the games collaboratored in.
	 *
	 * @param gamesCollaboratoredIn the new games collaboratored in
	 */
	public void setGamesCollaboratoredIn(List<Game> gamesCollaboratoredIn) {
		GamesCollaboratoredIn = gamesCollaboratoredIn;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId(){
		return this.id;
	}
	
	/**
	 * Adds the courses.
	 *
	 * @param content the content
	 */
	public void addCourses(Course content) {
		content.setTeacher(this);
		CoursesCreated.add(content);
	}
	
	/**
	 * Gets the courses created.
	 *
	 * @return the courses created
	 */
	public List<Course> getCoursesCreated() {
		return CoursesCreated;
	}
	
	/**
	 * Gets the courses registedin.
	 *
	 * @return the courses registedin
	 */
	public List<Course> getCoursesRegistedin() {
		return CoursesRegistedin;
	}
	
	/**
	 * Adds the courses CollaboratoredIn.
	 *
	 * @param gameCollaboratoredIn the game collaboratored in
	 */
	public void addGameCollaboratoredIn(Game gameCollaboratoredIn) {
		gameCollaboratoredIn.addCollaborator(this);
		GamesCollaboratoredIn.add(gameCollaboratoredIn);
	}
	/**
	 * Adds the courses registedin.
	 *
	 * @param coursesRegistedin the courses registedin
	 */
	public void addCoursesRegistedin(Course coursesRegistedin) {
		coursesRegistedin.addStudent(this);
		CoursesRegistedin.add(coursesRegistedin);
	}
	
	/**
	 * Gets the notifications.
	 *
	 * @return the notifications
	 */
	public List<Notification> getNotifications() {
		return Notifications;
	}

	/**
	 * Adds the notifications.
	 *
	 * @param notification the notification
	 */
	public void addNotifications(Notification notification) {
		Notifications.add(notification);
	}

	/**
	 * Gets the scores.
	 *
	 * @return the scores
	 */
	public List<Score> getScores() {
		return Scores;
	}
	
	/**
	 * Adds the scores.
	 *
	 * @param score the score
	 */
	public void addScores(Score score) {
		score.setUser(this);
		Scores.add(score);
		
	}
	
}
