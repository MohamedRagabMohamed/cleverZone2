package guru.springframework.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	public static final PasswordEncoder PASSWORD_ENCODER =  new BCryptPasswordEncoder();
	@NotNull
	@NotEmpty
	private String userName;
	@NotNull
	@NotEmpty
	private String firstName;
	@NotNull
	@NotEmpty
	private String lastName;
	@NotNull
	@NotEmpty
	private String password;
	@NotNull
	@NotEmpty
	private String [] Roles;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Teacher",targetEntity = Course.class)
	private List<Course> CoursesCreated;
	
	
//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(name="USER_COURSE", 
//				joinColumns={@JoinColumn(name="EMPLOYEE_ID")}, 
//				inverseJoinColumns={@JoinColumn(name="MEETING_ID")})
//	private Set<Course> meetings = new HashSet<Course>();

	User(){
		id = null;
		CoursesCreated = new ArrayList<>();
	}
	public User(String userName , String firstName, String lastName , String password , String [] roles){
		this();
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.Roles = roles;
		this.password = PASSWORD_ENCODER.encode(password);
	}
	public String[] getRoles() {
		return Roles;
	}
	public void setRoles(String[] roles) {
		Roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}
	public Long getId(){
		return this.id;
	}
	
	public List<Course> getCourses() {
		return CoursesCreated;
	}
	public void addCourses(Course content) {
		content.setTeacher(this);
		CoursesCreated.add(content);
	}
	
}
