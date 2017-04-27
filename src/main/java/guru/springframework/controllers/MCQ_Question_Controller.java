package guru.springframework.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.MCQ_Question;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.MCQQuestionRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class MCQ_Question_Controller.
 */
@RestController
public class MCQ_Question_Controller {
	
	/** The MCQ question service. */
	MCQQuestionRepository MCQQuestionService;
	
	/** The MCQ game service. */
	MCQGameRepository MCQGameService;

	
	/**
	 * Instantiates a new MC Q question controller.
	 *
	 * @param mCQQuestionService the m CQ question service
	 * @param mCQGameService the m CQ game service
	 */
	@Autowired
	   public MCQ_Question_Controller(MCQQuestionRepository mCQQuestionService, MCQGameRepository mCQGameService) {
			MCQQuestionService = mCQQuestionService;
			MCQGameService = mCQGameService;
		}
	
		/**
		 * Checks if is valid user.
		 *
		 * @param question the question
		 * @return the boolean
		 */
		public Boolean isValidUser(MCQ_Question question){
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return question.getMCQ_TheGame().getCourse().getTeacher().getUserName().equals(userDetails.getUsername());
		}
	
	  //-------------------Retrieve Single MCQ_Question--------------------------------------------------------
    
	   /**
  	 * Gets the MCQ question.
  	 *
  	 * @param id the id
  	 * @return the MCQ question
  	 */
  	@RequestMapping(value = "/MCQQuestion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<MCQ_Question> getMCQQuestion(@PathVariable("id") long id) {
	       System.out.println("Fetching Course with ID " + id);
	       MCQ_Question question = MCQQuestionService.findOne(id);
	       if (question == null) {
	           System.out.println("Question with ID " + id + " Not Found");
	           return new ResponseEntity<MCQ_Question>(HttpStatus.NOT_FOUND);
	       }
	       return new ResponseEntity<MCQ_Question>(question, HttpStatus.OK);
	   }

	    
	    
	
	
	   //-------------------Retrieve All MCQ Questions --------------------------------------------------------
    
	   /**
   	 * List all MC Q question.
   	 *
   	 * @return the response entity
   	 */
   	@RequestMapping(value = "/MCQQuestion/", method = RequestMethod.GET)
	   public ResponseEntity<List<MCQ_Question>> listAllMCQ_Question() {
	       List<MCQ_Question> Questions = MCQQuestionService.findAll();
	       if(Questions.isEmpty()){
	           return new ResponseEntity<List<MCQ_Question>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	       }
	       return new ResponseEntity<List<MCQ_Question>>(Questions, HttpStatus.OK);
	   }
   //------------------- Update a MCQQuestion -------------------------
   
   /**
    * Update question.
    *
    * @param id the id
    * @param question the question
    * @return the response entity
    */
   @PreAuthorize("hasRole('ROLE_TEACHER')")
   @RequestMapping(value = "/MCQQuestion/{id}", method = RequestMethod.PUT)
   public ResponseEntity<MCQ_Question> updateQuestion(@PathVariable("id") long id, @RequestBody MCQ_Question question) {
       System.out.println("Updating MCQQuestion " + id);
       MCQ_Question currentQuestion = MCQQuestionService.findOne(id);
       
       if (currentQuestion == null) {
           System.out.println("Question with ID " + id + " not found");
           return new ResponseEntity<MCQ_Question>(HttpStatus.NOT_FOUND);
           
       }else if(!isValidUser(question)){
    	   System.out.println("tring to update Course not belonging to the user ");
    	   return new ResponseEntity<MCQ_Question>(HttpStatus.NOT_ACCEPTABLE);
    	   
       }
       
       currentQuestion.setAnswer(question.getAnswer());
       currentQuestion.setChoices(question.getChoices());
       currentQuestion.setQuestion(question.getQuestion());
       
       MCQQuestionService.save(currentQuestion);
       return new ResponseEntity<MCQ_Question>(currentQuestion, HttpStatus.OK);
   }

//------------------- Delete a MCQQuestion --------------------------------------------------------
   
   /**
 * Delete question.
 *
 * @param id the id
 * @return the response entity
 */
@PreAuthorize("hasRole('ROLE_TEACHER')")
   @RequestMapping(value = "/MCQQuestion/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<MCQ_Question> deleteQuestion(@PathVariable("id") long id) {
	   
       System.out.println("Fetching & Deleting MCQQuestion with ID " + id);
      
       MCQ_Question currentQuestion = MCQQuestionService.findOne(id);
       if (currentQuestion == null) {
           System.out.println("Unable to delete. MCQQuestion with ID " + id + " not found");
           return new ResponseEntity<MCQ_Question>(HttpStatus.NOT_FOUND);
       }else if(!isValidUser(currentQuestion)){
    	   System.out.println("tring to update MCQQuestion not belonging to the user ");
    	   return new ResponseEntity<MCQ_Question>(HttpStatus.NOT_ACCEPTABLE);
    	   
       }

       MCQQuestionService.delete(id);
       return new ResponseEntity<MCQ_Question>(HttpStatus.NO_CONTENT);
   }
   
   //-------------------Create a MCQQuestion--------------------------------------------------------
   
   /**
    * Creates the question.
    *
    * @param gameId the game id
    * @param Question the question
    * @param ucBuilder the uc builder
    * @return the response entity
    */
   @PreAuthorize("hasRole('ROLE_TEACHER')")
   @RequestMapping(value = "/MCQQuestion/{gameId}", method = RequestMethod.POST)
   public ResponseEntity<Void> createQuestion(@PathVariable("gameId") long gameId,@RequestBody MCQ_Question Question,    UriComponentsBuilder ucBuilder) {
       System.out.println("Creating MCQ Question ");
       MCQ_Game Game = MCQGameService.findOne(gameId);
       if(Game == null){
    	   System.out.println("A Game with id " + gameId + " was not found");
    	   return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
       }
       UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if(!Game.getCourse().getTeacher().getUserName().equals(userDetails.getUsername())){
    	   System.out.println("tring to Create MCQQuestion not belonging to the user ");
    	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
       }
       if(Question.getChoices().length != 4){
    	   System.out.println("tring to Create MCQQuestion havs choics not equal to 4 ");
    	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
       }       
       Game.addQuestion(Question);
       MCQQuestionService.save(Question);
       HttpHeaders headers = new HttpHeaders();
       headers.setLocation(ucBuilder.path("/mcqquestion/{id}").buildAndExpand(Question.getId()).toUri());
       return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
   }
}
