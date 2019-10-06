package in.nileskh.framework.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.nileskh.framework.blogapp.entities.Blog;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Optional<Blog> findByIdAndBlogAuthor(Integer blogId, Integer blogAuthor);
}
