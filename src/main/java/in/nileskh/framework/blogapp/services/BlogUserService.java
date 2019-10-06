package in.nileskh.framework.blogapp.services;

import in.nileskh.framework.blogapp.entities.BlogUser;
import in.nileskh.framework.blogapp.repositories.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogUserService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    public List<BlogUser> getAllUsers() {
        return blogUserRepository.findAll();
    }

    public BlogUser getUserById(Integer userId) {
        return blogUserRepository.findById(userId).orElse(null);
    }

    public BlogUser saveUser(BlogUser blogUser) {
        return blogUserRepository.save(blogUser);
    }

    public BlogUser updateUserById(Integer userId, BlogUser newUser) {
        return blogUserRepository.findById(userId)
                .map((BlogUser oldUser) -> {
                    oldUser.setEmail(newUser.getEmail());
                    oldUser.setFullName(newUser.getFullName());
                    oldUser.setUsername(newUser.getUsername());
                    oldUser.setStatus(newUser.getStatus());
                    return blogUserRepository.save(oldUser);
                }).orElse(null);
    }

    public ResponseEntity<?> deleteUserById(Integer userId) {
        blogUserRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
