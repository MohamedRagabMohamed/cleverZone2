package guru.springframework.bootstrap;

import guru.springframework.domain.Course;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.MCQ_Question;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.TF_Question;
import guru.springframework.domain.User;
import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.MCQQuestionRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.TFQuestionRepository;
import guru.springframework.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userService;
    private CourseRepository CourseService;
    private MCQGameRepository mcqService;
    private TFGameRepository tfService;
    private MCQQuestionRepository mcqQuestionService;
    private TFQuestionRepository tfQuestionService;

    
  //  private Logger log = Logger.getLogger(DatabaseLoader.class);


    @Autowired
	public DatabaseLoader(UserRepository userService, CourseRepository courseService, MCQGameRepository mcqService,
			TFGameRepository tfService, MCQQuestionRepository mcqQuestionService,
			TFQuestionRepository tfQuestionService) {
		this.userService = userService;
		CourseService = courseService;
		this.mcqService = mcqService;
		this.tfService = tfService;
		this.mcqQuestionService = mcqQuestionService;
		this.tfQuestionService = tfQuestionService;
	}
    
    
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	ArrayList<User> UsersData = new ArrayList<User>(); 
    	User MCQTeacher = new User("T","nour" , "ahmed" , "P" ,new String[] {"ROLE_TEACHER","ROLE_ADMIN"} );
    	User TFTeacher = new User("TT","nour1" , "ahmed1" , "P" ,new String[] {"ROLE_TEACHER"} );
    	User Student = new User("S","nour1" , "ahmed1" , "P" ,new String[] {"ROLE_STUDENT"} );

		ArrayList<Course> CoursesData = new ArrayList<Course>(); 
		Course firstCourse = new Course("Arabic","the aradbic","thea arcbi");
		Course secondCourse = new Course("Math","the Mathvvve","tsdghea arcbi");
		Course thirdCourse = new Course("Programming","the Programmingved","thea ardsdfbcbi");
		Course fourthCourse = new Course("English","the Eng","ee eeee21");
		
        MCQ_Game theFirstGame = new MCQ_Game("Game1", "the desc", "imageSrc1");
        TF_Game theSecondGame = new TF_Game("Game2", "thesdv desc", "imageSrc2");
        MCQ_Game theThirdGame = new MCQ_Game("Game3", "the desdvsc", "isdmageSrc3");
        TF_Game thefourthGame = new TF_Game("Game4", "thesdv desc", "imageSrc2");
        
        TF_Question firstQuestion = new TF_Question("Question1", "answer1");
        String choices [] = new String [4];
        choices[0]="1";
        choices[1]="2";
        choices[2]="3";
        choices[3]="4";
        MCQ_Question secondQuestion = new MCQ_Question("Question2", "Answer",choices);
        

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
        
        UsersData.add(TFTeacher);
        UsersData.add(MCQTeacher);
        UsersData.add(Student);
        System.out.println(TFTeacher.getCoursesCreated().size());
        System.out.println(MCQTeacher.getCoursesCreated().size());
        /*mcqQuestionService.save(secondQuestion);
        tfQuestionService.save(firstQuestion);
        tfService.save(thefourthGame);tfService.save(theSecondGame);
        mcqService.save(theFirstGame);mcqService.save(theThirdGame);
        CourseService.save(CoursesData);*/ 	
        userService.save(UsersData);
    }



}
