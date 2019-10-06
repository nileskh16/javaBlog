package in.nileskh.framework.blogapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "author", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BlogUser blogAuthor;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name = "text", nullable = false)
	private String blogText;
	
	@Column(name = "created")
	@CreatedDate
	private Date createdDate;
	
	@Column
	private int likes;
	
	public Blog() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BlogUser getBlogAuthor() {
		return blogAuthor;
	}

	public void setBlogAuthor(BlogUser blogAuthor) {
		this.blogAuthor = blogAuthor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBlogText() {
		return blogText;
	}

	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}
