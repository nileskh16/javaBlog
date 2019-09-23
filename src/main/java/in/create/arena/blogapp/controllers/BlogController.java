package in.create.arena.blogapp.controllers;

import in.create.arena.blogapp.entities.Blog;
import in.create.arena.blogapp.exceptions.BlogException;
import in.create.arena.blogapp.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Blog> getBlogList() {
        return blogService.getAllBlog();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Blog getBlogById(@PathVariable(name="id") Integer blogId) throws BlogException {
        Blog blog = blogService.getBlogById(blogId);
        if (blog != null) {
            return blog;
        } else {
            throw new BlogException("No blog found with the ID");
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBlog(@RequestBody Blog blog) throws BlogException {
        try {
            Blog savedBlog = blogService.saveNewBlog(blog);
            return savedBlog != null ? "Blog was saved" : "Blog was not saved";
        } catch(Exception ex) {
            throw new BlogException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateBlogById(@PathVariable(name="id") Integer blogId, @RequestBody Blog blog) throws BlogException {
        Blog updatedBlog = blogService.updateBlogById(blog, blogId);
        if (updatedBlog != null) {
            return "Blog was updated";
        } else throw new BlogException("No blog found for the given id");
    }
}
