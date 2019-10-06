package in.nileskh.framework.blogapp.controllers;

import java.util.List;

import in.nileskh.framework.blogapp.entities.BlogUser;
import in.nileskh.framework.blogapp.repositories.BlogUserRepository;
import in.nileskh.framework.blogapp.response.ResponseTemplate;
import in.nileskh.framework.blogapp.response.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.nileskh.framework.blogapp.entities.Blog;
import in.nileskh.framework.blogapp.exceptions.BlogException;
import in.nileskh.framework.blogapp.services.BlogService;

@RestController
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private BlogUserRepository blogUserRepository;

	@Autowired
	private RestTemplate<ResponseTemplate> restTemplate;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Blog> getAllBlogs() {
		return blogService.getAllBlogs();
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Blog getBlogById(@PathVariable(name="id") int blogId) throws BlogException{
		Blog savedBlog = blogService.getBlogById(blogId);
		if (savedBlog != null) {
			return savedBlog;
		} else {
			throw new BlogException("No blog found for this id");
		}
	}

	@RequestMapping(value = "/create/{userId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseTemplate> createBlog(
			@PathVariable(name = "userId") Integer userId,
			@RequestBody Blog blog
	) throws BlogException {
		if (!blogUserRepository.existsById(userId)) {
			throw new BlogException("User with id does not exist");
		}
		ResponseTemplate res = new ResponseTemplate();
		Blog savedBlog = blogService.saveBlog(blog, userId);
		if (savedBlog != null) {
			res.setStatusCode(HttpStatus.OK.value());
			res.setMessage("Blog was saved successfully");
			res.setData(savedBlog);
		} else {
			res.setStatusCode(HttpStatus.BAD_REQUEST.value());
			res.setMessage("Blog was not saved");;
		}
		return restTemplate.response(res, HttpStatus.resolve(res.getStatusCode()));
	}

	@RequestMapping(value = "/delete/{userId}/{blogId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBlogByUserId(
			@PathVariable(name = "userId") Integer userId,
			@PathVariable(name = "blogId") Integer blogId
	) throws BlogException {
		if (!blogUserRepository.existsById(userId)) {
			throw new BlogException("User does not exists with Id");
		}
		return blogService.deletedByIdAndAuthor(userId, blogId);
	}
}
