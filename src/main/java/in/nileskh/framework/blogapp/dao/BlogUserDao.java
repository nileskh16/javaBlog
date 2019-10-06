package in.nileskh.framework.blogapp.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogUserDao {
    private String fullName;
    private String username;
    private String email;
    private String password;
}
