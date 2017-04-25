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

import guru.springframework.repositories.CourseRepository;
import guru.springframework.repositories.MCQGameRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.domain.Course;
import guru.springframework.domain.MCQ_Game;
import guru.springframework.domain.TF_Game;
import guru.springframework.domain.User;

@RestController
public class MCQ_Game_Controller {

	MCQGameRepository MCQService;
	UserRepository userService;
	CourseRepository courseService;
	@Autowired
    public MCQ_Game_Controller(MCQGameRepository m, UserRepository userService,
			CourseRepository courseService) {
		this.MCQService = m;
		this.userService = userService;
		this.courseService = courseService;
	}
	//-------------------Retrieve MCQ Game -------------------------
    @RequestMapping(value = "/mcqgame/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MCQ_Game > > getGame() {
        System.out.println("Fetching all mcq Games");
        List<MCQ_Game > game = (ArrayList<MCQ_Game>) MCQService.findAll();
        if (game == null) {
            System.out.println("no game  found");
            return new ResponseEntity<List<MCQ_Game >>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<MCQ_Game > >(game,HttpStatus.OK);
    }
    //-------------------Retrieve MCQ Game ------------------------------
    
    @RequestMapping(value = "/mcqgame/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MCQ_Game> getGame(@PathVariable("id") long id) {
        System.out.println("Fetching mcqGame with id " + id);
        MCQ_Game game = MCQService.findOne(id);
        if (game == null) {
            System.out.println("game with id " + id + " not found");
            return new ResponseEntity<MCQ_Game>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MCQ_Game>(game, HttpStatus.OK);
    }
    


	//-------------------Create a MCQ game-------------------------------------

	@PreAuthorize("hasRole('ROLE_TEACHER')")
    @RequestMapping(value = "/mcqgame/{courseId}", method = RequestMethod.POST)
    public ResponseEntity<Void> createMCQGame(@PathVariable("courseId") long courseId,@RequestBody MCQ_Game Game,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating mcq Game " + Game.getName());
        if ( MCQService.findByname(Game.getName()) != null  ) {
            System.out.println("A MCQ Game with name " + Game.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        Course course=courseService.findOne(courseId);
        if(course==null){
            System.out.println("Course with ID " + courseId + " Not Found");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        User user = course.getTeacher();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUserName().equals(userDetails.getUsername())){
     	   System.out.println("tring to create a game to a Course not belonging to the user ");
     	   return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
        course.addContents(Game);
        MCQService.save(Game);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    //------------------- Delete a mcq game --------------------------------
    
    @RequestMapping(value = "/mcqgame/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<MCQ_Game> deleteGame(@PathVariable("id") long id) {
    	MCQ_Game game = MCQService.findOne(id);
        if (game == null) {
            System.out.println("Unable to delete. game with id " + id + " not found");
            return new ResponseEntity<MCQ_Game>(HttpStatus.NOT_FOUND);
        }
        User user = game.getCourse().getTeacher();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUserName().equals(userDetails.getUsername())){
     	   System.out.println("tring to delete a game to a Course not belonging to the user ");
     	   return new ResponseEntity<MCQ_Game>(HttpStatus.NOT_ACCEPTABLE);
        }
        MCQService.delete(id);
        return new ResponseEntity<MCQ_Game>(HttpStatus.NO_CONTENT);
    }
    
    
    //------------------- Update a mcq Game --------------------------------------------------------
    
    @RequestMapping(value = "/mcqgame/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MCQ_Game> updateGame(@PathVariable("id") long id, @RequestBody TF_Game game) {
        System.out.println("Updating Game " + id);
        MCQ_Game currentGame = MCQService.findOne(id);
        if (currentGame==null) {
            System.out.println("Game with id " + id + " not found");
            return new ResponseEntity<MCQ_Game>(HttpStatus.NOT_FOUND);
        }
        User user = currentGame.getCourse().getTeacher();
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.getUserName().equals(userDetails.getUsername())){
     	   System.out.println("tring to update a game to a Course not belonging to the user ");
     	   return new ResponseEntity<MCQ_Game>(HttpStatus.NOT_ACCEPTABLE);
        }
        currentGame.setCourse(game.getCourse());
        currentGame.setName(game.getName());
        currentGame.setdescption(game.getdescption());
        currentGame.setImageSrc(game.getImageSrc());
        MCQService.save(currentGame);
        return new ResponseEntity<MCQ_Game>(currentGame, HttpStatus.OK);
    }
    
}
