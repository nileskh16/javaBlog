package in.nileskh.framework.blogapp.services;

import java.util.List;

import in.nileskh.framework.blogapp.entities.BlogUser;
import in.nileskh.framework.blogapp.exceptions.BlogException;
import in.nileskh.framework.blogapp.repositories.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.nileskh.framework.blogapp.entities.Blog;
import in.nileskh.framework.blogapp.repositories.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepo;

	@Autowired
	private BlogUserRepository blogUserRepository;
	
	public List<Blog> getAllBlogs() {
		return blogRepo.findAll();
	}
	
	public Blog getBlogById(int blogId) {
		return blogRepo.findById(blogId).orElse(null);
	}

	public ResponseEntity<?> deletedByIdAndAuthor(Integer blogId, Integer userId) throws BlogException {
//		return blogRepo.findByIdAndBlogAuthor(blogId, userId)
//				.map((Blog foundBlog) -> {
//					blogRepo.delete(foundBlog);
//					return ResponseEntity.ok().build();
//				})
//				.orElseThrow(() -> new BlogException("No blog found to delete"));

		blogRepo.deleteById(blogId);
		return ResponseEntity.ok().build();
	}
	
	public Blog saveBlog(Blog blog, Integer userId) throws BlogException {
		return blogUserRepository.findById(userId)
				.map((BlogUser savedUser) -> {
					blog.setBlogAuthor(savedUser);
					blog.setLikes(0);
					return blogRepo.save(blog);
				})
				.orElseThrow(() -> new BlogException("No user exists with"));
	}
	
	public Blog updateBlogById(Blog newBlog, int blogId) {
		return blogRepo
				.findById(blogId)
				.map((Blog savedBlog) -> {
					savedBlog.setBlogAuthor(newBlog.getBlogAuthor());
					savedBlog.setBlogText(newBlog.getBlogText());
					savedBlog.setTitle(newBlog.getTitle());
					savedBlog.setLikes(newBlog.getLikes());
					return blogRepo.save(savedBlog);
				}).orElseThrow(() -> null);
	}
}
