package guru.springframework.bootstrap;

import guru.springframework.domain.Course;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.MCQ_Question;
import guru.springframework.domain.Score;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.TF_Question;
import guru.springframework.domain.User;
import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.MCQQuestionRepository;
import guru.springframework.repositories.ScoreRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.TFQuestionRepository;
import guru.springframework.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseLoader.
 */
@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    /** The user service. */
    private UserRepository userService;
    
    /** The Course service. */
    private CourseRepository CourseService;
    
    /** The mcq service. */
    private MCQGameRepository mcqService;
    
    /** The tf service. */
    private TFGameRepository tfService;
    
    /** The mcq question service. */
    private MCQQuestionRepository mcqQuestionService;
    
    /** The tf question service. */
    private TFQuestionRepository tfQuestionService;

    private ScoreRepository scoreService; 
  //  private Logger log = Logger.getLogger(DatabaseLoader.class);


    /**
   * Instantiates a new database loader.
   *
   * @param userService the user service
   * @param courseService the course service
   * @param mcqService the mcq service
   * @param tfService the tf service
   * @param mcqQuestionService the mcq question service
   * @param tfQuestionService the tf question service
   */
  @Autowired
	public DatabaseLoader(UserRepository userService, CourseRepository courseService, MCQGameRepository mcqService,
			TFGameRepository tfService, MCQQuestionRepository mcqQuestionService,
			TFQuestionRepository tfQuestionService,ScoreRepository scoreService) {
		this.userService = userService;
		CourseService = courseService;
		this.mcqService = mcqService;
		this.tfService = tfService;
		this.mcqQuestionService = mcqQuestionService;
		this.tfQuestionService = tfQuestionService;
		this.scoreService = scoreService;
	}
    
    
    
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	ArrayList<User> UsersData = new ArrayList<User>(); 
    	User MCQTeacher = new User("T","nour" , "ahmed" , "P" ,new String[] {"ROLE_TEACHER","ROLE_ADMIN"} );
    	User TFTeacher = new User("TT","nour1" , "ahmed1" , "P" ,new String[] {"ROLE_TEACHER"} );
    	User Student = new User("S","nour1" , "ahmed1" , "P" ,new String[] {"ROLE_STUDENT"} );
    	User Student2 = new User("S2","nour1" , "ahmed1" , "P" ,new String[] {"ROLE_STUDENT"} );

		ArrayList<Course> CoursesData = new ArrayList<Course>(); 
		Course firstCourse = new Course("Arabic","the aradbic","thea arcbi");
		Course secondCourse = new Course("Math","the Mathvvve","tsdghea arcbi");
		Course thirdCourse = new Course("Programming","the Programmingved","thea ardsdfbcbi");
		Course fourthCourse = new Course("English","the Eng","ee eeee21");
		
        MCQ_Game theFirstGame = new MCQ_Game("Game1", "the desc", "imageSrc1",10);
        TF_Game theSecondGame = new TF_Game("Game2", "thesdv desc", "imageSrc2",10);
        MCQ_Game theThirdGame = new MCQ_Game("Game3", "the desdvsc", "isdmageSrc3",10);
        TF_Game thefourthGame = new TF_Game("Game4", "thesdv desc", "imageSrc2",10);
        // testing not magic numbers 
        TF_Question firstQuestion = new TF_Question("Question1", "TRUE");
        String choices [] = new String [4];
        choices[0]="CHOICE1";
        choices[1]="CHOICE2";
        choices[2]="CHOICE3";
        choices[3]="CHOICE4";
        MCQ_Question secondQuestion = new MCQ_Question("Question2", "CHOICE2",choices);
        

        theFirstGame.addQuestion(secondQuestion);
        theSecondGame.addQuestion(firstQuestion);
        theThirdGame.addQuestion(secondQuestion);
        thefourthGame.addQuestion(firstQuestion);
  	
        
        firstCourse.addContents(theFirstGame);
        secondCourse.addContents(theSecondGame);
        thirdCourse.addContents(theThirdGame);
        fourthCourse.addContents(thefourthGame);
        
        CoursesData.add(firstCourse);
        CoursesData.add(secondCourse);
        CoursesData.add(thirdCourse);
        CoursesData.add(fourthCourse);
        
        MCQTeacher.addCourses(firstCourse);
        MCQTeacher.addCourses(thirdCourse);
        TFTeacher.addCourses(secondCourse);
        TFTeacher.addCourses(fourthCourse);
        
        Student.addCoursesRegistedin(firstCourse);
        Student.addCoursesRegistedin(secondCourse);
        Student.addCoursesRegistedin(thirdCourse);
        Student.addCoursesRegistedin(fourthCourse);
        
        Student2.addCoursesRegistedin(firstCourse);
        Student2.addCoursesRegistedin(secondCourse);
        Student2.addCoursesRegistedin(thirdCourse);
        Student2.addCoursesRegistedin(fourthCourse);
        
//        Score StudentWithFirstGame = new Score(theFirstGame);
//        StudentWithFirstGame.setScoreValue(100);
//        Student.addScores(StudentWithFirstGame);
        
        UsersData.add(TFTeacher);
        UsersData.add(MCQTeacher);
        UsersData.add(Student);
        UsersData.add(Student2);
        System.out.println(TFTeacher.getCoursesCreated().size());
        System.out.println(MCQTeacher.getCoursesCreated().size());
        /*mcqQuestionService.save(secondQuestion);
        tfQuestionService.save(firstQuestion);
        tfService.save(thefourthGame);tfService.save(theSecondGame);
        mcqService.save(theFirstGame);mcqService.save(theThirdGame);
        CourseService.save(CoursesData);*/ 	
        userService.save(UsersData);
       // scoreService.save(StudentWithFirstGame);
    }



}
