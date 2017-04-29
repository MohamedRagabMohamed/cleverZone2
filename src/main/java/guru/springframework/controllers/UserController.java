package guru.springframework.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import guru.springframework.domain.Game;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.Score;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.User;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.TFGameRepository;
import guru.springframework.repositories.UserRepository;;

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
	
	/**
	 * Instantiates a new user controller.
	 *
	 * @param userService the user service
	 */
	@Autowired
	public UserController(UserRepository userService,MCQGameRepository mcqService,TFGameRepository tfService) {
		this.userService = userService;
		this.mcqService = mcqService;
		this.tfService = tfService;
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
    @RequestMapping(value = "/user/{userId}/{gameId}/{score}", method = RequestMethod.GET)
    public ResponseEntity<Void> scoreUser(@PathVariable("userId") long userId,@PathVariable("gameId") long gameId,
    		@PathVariable("score") long scoreValue) {
        System.out.println("Creating Score with userId " + userId + " and gameId " +gameId );
        User user = userService.findOne(userId);
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
 
}