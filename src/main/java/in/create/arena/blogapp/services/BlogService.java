package in.create.arena.blogapp.services;

import in.create.arena.blogapp.entities.Blog;
import in.create.arena.blogapp.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlog() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Integer blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    public Blog saveNewBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlogById(Blog newBlog, Integer blogId) {
        return blogRepository.findById(blogId)
                .map((Blog savedBlog) -> {
                    savedBlog.setBlogAuthor(newBlog.getBlogAuthor());
                    savedBlog.setBlogText(newBlog.getBlogText());
                    savedBlog.setBlogTitle(newBlog.getBlogTitle());
                    savedBlog.setNumOfLikes(newBlog.getNumOfLikes());
                    return blogRepository.save(savedBlog);
                }).orElse(null);
    }
}
