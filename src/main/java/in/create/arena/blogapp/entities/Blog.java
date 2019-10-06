package in.create.arena.blogapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="title")
    private String blogTitle;

    @Column(name="text")
    private String blogText;

    @Column(name="author")
    private String blogAuthor;

    @Column(name="createdDate")
    private Date createdDate = new Date();

    @Column(name="likes")
    private int numOfLikes;
}
