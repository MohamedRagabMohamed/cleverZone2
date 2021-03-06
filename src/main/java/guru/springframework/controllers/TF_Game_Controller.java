package guru.springframework.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.NewGameNotificationRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.domain.Comment;
import guru.springframework.domain.Course;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.NewGameNotification;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.User;

// TODO: Auto-generated Javadoc
/**
 * The Class TF_Game_Controller.
 */
@RestController
public class TF_Game_Controller {

	/** The T F repository. */
	TFGameRepository TF_Repository;
	
	/** The user service. */
	UserRepository userService;
	
	/** The course service. */
	CourseRepository courseService;
	
	/** The notify service. */
	NewGameNotificationRepository notifyService;
	
	/**
	 * Instantiates a new t F game controller.
	 *
	 * @param tF_Repository the t F repository
	 * @param userService the user service
	 * @param courseService the course service
	 * @param notifyService the notify service
	 */
	@Autowired
    public TF_Game_Controller(TFGameRepository tF_Repository, UserRepository userService,
			CourseRepository courseService,NewGameNotificationRepository notifyService ) {
		TF_Repository = tF_Repository;
		this.userService = userService;
		this.courseService = courseService;
		this.notifyService =notifyService;
	}
	
	/**
	 * Notify users.
	 *
	 * @param courseId the course id
	 * @param gameId the game id
	 */
	private void NotifyUsers(Long courseId,Long gameId){
		Course course =  courseService.getOne(courseId);
		List<User> users = course.getUsers();
		List<NewGameNotification> notifys = new ArrayList<NewGameNotification>();
		for(int i=0;i<users.size();i++){
			NewGameNotification tmp = new NewGameNotification(users.get(i), course.getName(),gameId );
			users.get(i).addNotifications(tmp);
			notifys.add(tmp);
		}
		notifyService.save(notifys);
		return;
	}
	
	
    /**
     * Checks if is owner.
     *
     * @param user the user
     * @return true, if is owner
     */
    private boolean isOwner(User user){
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUserName().equals(userDetails.getUsername());
    }
    
    /**
     * Checks if is collaborator.
     *
     * @param game the game
     * @return true, if is collaborator
     */
    private boolean isCollaborator(TF_Game game){
    	boolean valid=false;
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(User user : game.getCollaborators()){
        	 valid|=user.getUserName().equals(userDetails.getUsername());
        }
        return valid;
    }

	
    /**
     * Cancel game.
     *
     * @param gameId the game id
     * @param state the state
     * @return the response entity
     */
    //-------------------Cancel specific TF game -------------------------
    @RequestMapping(value = "/canceltfgame/{gameId}/{state}", method = RequestMethod.POST)
    public ResponseEntity<Void> cancelGame(@PathVariable("gameId") long gameId , @PathVariable("state") boolean state) {
    	TF_Game game = TF_Repository.findOne(gameId);
        if (game == null) {
            System.out.println("no game  found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        game.setCancled(state);
        TF_Repository.save(game);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	/**
	 * Gets the comment.
	 *
	 * @param gameId the game id
	 * @return the comment
	 */
	//-------------------Retrieve all TFGame Comments  -------------------------
    @RequestMapping(value = "/tfgamecomment/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<	List<Comment> > getcomment(@PathVariable("gameId") long gameId) {
        System.out.println("Fetching all tf Games");
        TF_Game game = TF_Repository.findOne(gameId);
        if (game == null) {
            System.out.println("no game  found");
            return new ResponseEntity<List<Comment >>(HttpStatus.NOT_FOUND);
        }
        List<Comment>comments = game.getComments();
        return new ResponseEntity<List<Comment > >(comments,HttpStatus.OK);
    }
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	//-------------------Retrieve all TF Games -------------------------
    @RequestMapping(value = "/tfgame/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TF_Game > > getGame() {
        System.out.println("Fetching all tf Games");
        List<TF_Game > game = (ArrayList<TF_Game>) TF_Repository.findAll();
        if (game == null) {
            System.out.println("no game  found");
            return new ResponseEntity<List<TF_Game >>(HttpStatus.NOT_FOUND);
        }
        
        for(int i = 0 ; i < game.size() ; i++)
        {
        	if(game.get(i).isCancled() == true)
        	{
        		game.remove(i);
        	}
        }
        
        return new ResponseEntity<List<TF_Game > >(game,HttpStatus.OK);
    }
    //-------------------Retrieve TF Game ------------------------------

    /**
     * Gets the game.
     *
     * @param id the id
     * @return the game
     */
    @RequestMapping(value = "/tfgame/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TF_Game> getGame(@PathVariable("id") long id) {
        System.out.println("Fetching tfGame with id " + id);
        TF_Game game = TF_Repository.findOne(id);
        if (game == null) {
            System.out.println("game with id " + id + " not found");
            return new ResponseEntity<TF_Game>(HttpStatus.NOT_FOUND);
        }
        
        if(game.isCancled() == true)
        {
        	return new ResponseEntity<TF_Game>(HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<TF_Game>(game, HttpStatus.OK);
    }
    
    //-------------------Create a TF game-------------------------------------

	/**
     * Creates the TF game.
     *
     * @param courseId the course id
     * @param Game the game
     * @param ucBuilder the uc builder
     * @return the response entity
     */
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/tfgame/{courseId}", method = RequestMethod.POST)
    public ResponseEntity<Void> createTFGame(@PathVariable("courseId") long courseId,@RequestBody TF_Game Game,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating T/F Game " + Game.getName());
        if ( TF_Repository.findByname(Game.getName()) != null  ) {
            System.out.println("A Game with name " + Game.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        Course course=courseService.findOne(courseId);
        if(course==null){
            System.out.println("Course with ID " + courseId + " Not Found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        User user = course.getTeacher();
        if(!isOwner(user)){
     	   System.out.println("tring to create a game to a Course not belonging to the user ");
     	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
        course.addContents(Game);
        Game.setType("TF");
        TF_Repository.save(Game);
        NotifyUsers(courseId,Game.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tfgame/{id}").buildAndExpand(Game.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    
  //-------------------Copy TF game-------------------------------------

  		/**
   * Copy TF game.
   *
   * @param courseId the course id
   * @param gameId the game id
   * @return the response entity
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  	    @RequestMapping(value = "/tfgame/{courseId}/{gameId}", method = RequestMethod.GET)
  	    public ResponseEntity<Void> copyTFGame(@PathVariable("courseId") long courseId,@PathVariable("gameId") long gameId) {
  	        //System.out.println("Copying tf Game " + gameId.getName());
  	        
  	        TF_Game Game = TF_Repository.findOne(gameId);
  	        if (Game == null){
  	        	System.out.println("Game with ID " + gameId + " Not Found");
  	        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  	        }
  	        
  	        Course course = courseService.findOne(courseId);
  	        if(course==null){
  	            System.out.println("Course with ID " + courseId + " Not Found");
  	            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  	        }
  	        
  	        User user = course.getTeacher();
  	        if(!isOwner(user)){
  	     	   System.out.println("trying to copy a game to a Course not belonging to the user ");
  	     	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
  	        }
  	        
  	        TF_Game gameCopy = new TF_Game();
  	        gameCopy.setName(Game.getName() + "-" + getSaltString());
  	        gameCopy.setdescption(Game.getdescption());
  	        gameCopy.setImageSrc(Game.getImageSrc());
  	        gameCopy.setType(Game.getType());
  	        gameCopy.setCourse(course);
  	        gameCopy.setCancled(false);
  	        gameCopy.setTotalTime(Game.getTotalTime());
  	        for(int i = 0 ; i < Game.getQuestions().size(); i++)
  	        {
  	        	gameCopy.addQuestion(Game.getQuestions().get(i));
  	        }
  	        course.addContents(gameCopy);
  	        TF_Repository.save(gameCopy);
  	        NotifyUsers(courseId,gameCopy.getId());
  	        return new ResponseEntity<Void>(HttpStatus.CREATED);
  	    }
    
    
    
    
    
    
    
    
    //------------------- Delete a TF game --------------------------------
    
    /**
     * Delete game.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/tfgame/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TF_Game> deleteGame(@PathVariable("id") long id) {
    	TF_Game game = TF_Repository.findOne(id);
        if (game == null) {
            System.out.println("Unable to delete. game with id " + id + " not found");
            return new ResponseEntity<TF_Game>(HttpStatus.NOT_FOUND);
        }
        User user = game.getCourse().getTeacher();
        if(!isOwner(user)){
     	   System.out.println("tring to delete a game to a Course not belonging to the user ");
     	   return new ResponseEntity<TF_Game>(HttpStatus.NOT_ACCEPTABLE);
        }
        TF_Repository.delete(id);
        return new ResponseEntity<TF_Game>(HttpStatus.NO_CONTENT);
    }
    
    
    //------------------- Update a TF Game --------------------------------------------------------
    
    /**
     * Update game.
     *
     * @param id the id
     * @param game the game
     * @return the response entity
     */
    @RequestMapping(value = "/tfgame/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TF_Game> updateGame(@PathVariable("id") long id, @RequestBody TF_Game game) {
        System.out.println("Updating Game " + id);
        TF_Game currentGame = TF_Repository.findOne(id);
        if (currentGame==null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<TF_Game>(HttpStatus.NOT_FOUND);
        }
        User user = currentGame.getCourse().getTeacher();
        if(!isOwner(user) && !isCollaborator(currentGame)){
     	   System.out.println("tring to update a game to a Course not belonging to the user ");
     	   return new ResponseEntity<TF_Game>(HttpStatus.NOT_ACCEPTABLE);
        }
        currentGame.setCourse(currentGame.getCourse());
        currentGame.setName(game.getName());
        currentGame.setdescption(game.getdescption());
        currentGame.setImageSrc(game.getImageSrc());
        currentGame.setTotalTime(game.getTotalTime());
        TF_Repository.save(currentGame);
        return new ResponseEntity<TF_Game>(currentGame, HttpStatus.OK);
    }
    
  /**
   * Gets the salt string.
   *
   * @return the salt string
   */
  //------------------- Utility function --------------------------------------------------------
    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}
