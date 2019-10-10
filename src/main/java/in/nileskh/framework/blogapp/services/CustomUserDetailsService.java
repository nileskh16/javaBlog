package in.nileskh.framework.blogapp.services;

import in.nileskh.framework.blogapp.entities.BlogUser;
import in.nileskh.framework.blogapp.repositories.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private BlogUserRepository blogUserRepository;

    public CustomUserDetailsService() {
        super();
    }

    @PostConstruct
    public void completeSetup() {
        blogUserRepository = webApplicationContext.getBean(BlogUserRepository.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final BlogUser blogUser = blogUserRepository.findByUsername(username);
        if (blogUser != null) {
            return new PrincipalUser(blogUser);
        } else {
            throw new UsernameNotFoundException("No user found with the username");
        }
    }
}
