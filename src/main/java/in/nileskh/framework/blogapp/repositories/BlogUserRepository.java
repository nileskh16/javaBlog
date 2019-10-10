package in.nileskh.framework.blogapp.repositories;

import in.nileskh.framework.blogapp.entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {
    BlogUser findByUsername(String username);
}
