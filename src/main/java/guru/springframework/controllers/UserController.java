package guru.springframework.controllers;

import java.util.ArrayList;
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

import guru.springframework.domain.Comment;
import guru.springframework.domain.CommentNotification;
import guru.springframework.domain.Game;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.Notification;
import guru.springframework.domain.Score;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.User;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.repositories.NewCommentNotificationRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
public class UserController {
	//TODO make it only admin can use this controller

    /** The user service. */
	UserRepository userService;  //Service which will do all data retrieval/manipulation work
	MCQGameRepository mcqService;
	TFGameRepository tfService;
	NewCommentNotificationRepository newCommentNotificationService;
	
	/**
	 * Instantiates a new user controller.
	 *
	 * @param userService the user service
	 */
	@Autowired
	public UserController(UserRepository userService,MCQGameRepository mcqService,TFGameRepository tfService
			,NewCommentNotificationRepository newCommentNotificationService) {
		this.userService = userService;
		this.mcqService = mcqService;
		this.tfService = tfService;
		this.newCommentNotificationService = newCommentNotificationService;
	}
	
	
	private void NotifyUsers(Game game,String commenterName){
		List<User> users = game.getCourse().getUsers();
		List<CommentNotification> notifys = new ArrayList<CommentNotification>();
		for(int i=0;i<users.size();i++){
			CommentNotification tmp = new CommentNotification(users.get(i), commenterName,game.getId() );
			users.get(i).addNotifications(tmp);
			notifys.add(tmp);
		}
		newCommentNotificationService.save(notifys);
		return;
	}
	
	
	
	
	
	
	
	
     
    //-------------------Retrieve get Users Roles --------------------------------------------------------

    
    /**
     * Gets the user.
     *
     * @return the user
     */
    @RequestMapping(value = "/getuser/", method = RequestMethod.GET)
    public ResponseEntity<User> getUser() {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = userService.findByuserName(userDetails.getUsername());
        return new ResponseEntity<User>( user,HttpStatus.OK);
    }
 
 
	
	
	
    //-------------------Retrieve All Users--------------------------------------------------------

    
    /**
     * List all users.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    /**
     * Gets the user.
     *
     * @param id the id
     * @return the user
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
    
    /**
     * Creates the user.
     *
     * @param user the user
     * @param ucBuilder the uc builder
     * @return the response entity
     */
    @PreAuthorize("hasRole('ROLE_ANONYMOUS')")
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUserName());
 
        if (userService.findByuserName(user.getUserName()) != null) {
            System.out.println("A User with name " + user.getUserName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.save(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    /**
     * Update user.
     *
     * @param id the id
     * @param user the user
     * @return the response entity
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findOne(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUserName(user.getUserName());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        
        
        
        userService.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    /**
     * Delete user.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findOne(id);
        
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    
    //-------------------Score a User--------------------------------------------------------
    
    /**
     * Score the user.
     *
     * @param user the user
     * @return the response entity
     */
    @RequestMapping(value = "/user/{gameId}/{score}", method = RequestMethod.GET)
    public ResponseEntity<Void> scoreUser(@PathVariable("gameId") long gameId,@PathVariable("score") long scoreValue) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = userService.findByuserName(userDetails.getUsername());
        MCQ_Game game = mcqService.findOne(gameId);
        TF_Game game2 = tfService.findOne(gameId);
         
        if (user == null||(game == null && game2 == null)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        if(game != null){
        	Score score = new Score(game);
        	score.setScoreValue(scoreValue);
        	user.addScores(score);
        }else if(game2 != null){
        	Score score = new Score(game2);
        	score.setScoreValue(scoreValue);
        	user.addScores(score);
        }
 
        userService.save(user);
 
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    //	------------------- add a user to collaborated list --------------------------------
    @RequestMapping(value = "/user/{userId}/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addCollaboratedUser(@PathVariable("userId") long userId,@PathVariable("gameId") long gameId){
        User user = userService.findOne(userId);
        MCQ_Game game1 = mcqService.findOne(gameId);
        TF_Game game2 = tfService.findOne(gameId);
         
        if (user == null||(game1 == null && game2 == null)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        Game game;
        if(game1 == null){
        	game=new MCQ_Game();
        	game=game1;
        }
        else{
        	game=new TF_Game();
        	game=game2;
        }
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!game.getCourse().getTeacher().getUserName().equals(userDetails.getUsername())){
      	   System.out.println("tring to update a game to a Course not belonging to the user ");
      	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
        user.addGameCollaboratoredIn(game);
        userService.save(user);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    
    //	------------------- add commet --------------------------------
    @RequestMapping(value = "/comment/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addComment(@PathVariable("gameId") long gameId,@RequestBody Comment comment){
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	User user = userService.findByuserName(userDetails.getUsername());
        MCQ_Game game1 = mcqService.findOne(gameId);
        TF_Game game2 = tfService.findOne(gameId);
         
        if (user == null||(game1 == null && game2 == null)) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        Comment Newcomment;
        System.out.println(comment.getText());
        if(game1 != null){
        	Newcomment=new Comment(game1, comment.getText());
        }
        else{
        	Newcomment=new Comment(game1, comment.getText());
        }
        user.addComment(Newcomment);
        userService.save(user);
        if(game1 != null){
        	NotifyUsers(game1, user.getUserName());
        }
        else{
        	NotifyUsers(game2, user.getUserName());
        }
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    
    

    
}