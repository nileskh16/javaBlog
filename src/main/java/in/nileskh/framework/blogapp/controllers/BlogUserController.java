package in.nileskh.framework.blogapp.controllers;

import in.nileskh.framework.blogapp.dao.BlogUserDao;
import in.nileskh.framework.blogapp.entities.BlogUser;
import in.nileskh.framework.blogapp.response.ResponseTemplate;
import in.nileskh.framework.blogapp.response.RestTemplate;
import in.nileskh.framework.blogapp.services.BlogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class BlogUserController {

    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate<ResponseTemplate> restTemplate;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<ResponseTemplate> getAllUsers() throws Exception {
        List<BlogUser> allUsers = blogUserService.getAllUsers();
        ResponseTemplate res = null;
        if (allUsers != null && allUsers.size() > 0) {
            res = prepareResponse("Users found", HttpStatus.OK, allUsers);
        } else {
            res = prepareResponse("No users found", HttpStatus.OK, null);
        }
        return restTemplate.response(res, HttpStatus.resolve(res.getStatusCode()));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseTemplate> getBlogUserById(@PathVariable(name = "id") Integer userId) {
        BlogUser savedUser = blogUserService.getUserById(userId);
        ResponseTemplate res;
        if (savedUser != null) {
            res = prepareResponse("User found", HttpStatus.OK, savedUser);
        } else {
            res = prepareResponse("No user found with this id", HttpStatus.BAD_REQUEST, null);
        }
        return restTemplate.response(res, HttpStatus.resolve(res.getStatusCode()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseTemplate> createUser(@RequestBody BlogUserDao blogUserDao) {
        BlogUser savedUser = blogUserService.saveUser(mapDaoToEntity(blogUserDao));
        if (savedUser != null) {
            return restTemplate.response(prepareResponse("User saved", HttpStatus.OK, savedUser), HttpStatus.OK);
        } else {
            return restTemplate.response(prepareResponse("Failed to save user", HttpStatus.BAD_REQUEST, null), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") Integer userId) {
        return blogUserService.deleteUserById(userId);
    }

    private ResponseTemplate prepareResponse(String message, HttpStatus httpStatus, Object respBody) {
        return new ResponseTemplate(message,
                httpStatus.value(),
                respBody);
    }

    private BlogUser mapDaoToEntity(BlogUserDao blogUserDao) {
        BlogUser blogUser = new BlogUser();
        blogUser.setUsername(blogUserDao.getUsername());
        blogUser.setFullName(blogUserDao.getFullName());
        blogUser.setEmail(blogUserDao.getEmail());
        blogUser.setPassword(passwordEncoder.encode(blogUserDao.getPassword()));
        blogUser.setStatus("active");
        return blogUser;
    }
}
