package com.example.hibonetomany.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "post")
public class Post {
	 
	@Id
	@GeneratedValue
	private Long id;
	  
	    private String title;
	    private String review;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id")
	    private Users user;
	    
	    @OneToMany(cascade = CascadeType.ALL, 
		        mappedBy = "post" )
		    private List<PostComment> comments;
	    
	    
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
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		public List<PostComment> getComments() {
			return comments;
		}
		public void setComments(List<PostComment> comments) {
			this.comments = comments;
		}
	    
	    
	 

}
