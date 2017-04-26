package guru.springframework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import guru.springframework.repositories.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class DetailsService.
 */
@Component
public class DetailsService implements UserDetailsService {
	
	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		guru.springframework.domain.User theUser = userRepository.findByuserName(username);
		if(theUser == null){
			throw new UsernameNotFoundException(username +" not found");
		}
		return new User(theUser.getUserName(), theUser.getPassword(), AuthorityUtils.createAuthorityList(theUser.getRoles()));
	}

  
}