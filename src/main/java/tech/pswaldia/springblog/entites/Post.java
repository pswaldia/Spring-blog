package tech.pswaldia.springblog.entites;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column
	private String title;
	@Lob
	@Column
	@NotBlank
	private String Content;
	@Column
	private Instant createdOn;
	@Column
	private Instant updatedOn;
	@NotBlank
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	public Post() {
		
	}
	
	public Post(Long id, @NotBlank String title, @NotBlank String content, Instant createdOn, Instant updatedOn,
			@NotBlank User user) {
		super();
		this.id = id;
		this.title = title;
		Content = content;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
