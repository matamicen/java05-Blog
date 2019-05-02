package com.example.hibonetomany.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name = "post_comment")
public class PostComment {
	    @Id
	    @GeneratedValue
	    private Long id;
	 
	    private String comment;
	    
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "post_id")
	    private Post post;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id")
	    private Users user;
	    
	    

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

		public Users getUser() {
			return user;
		}

		public void setUser(Users user) {
			this.user = user;
		}

	    
	    
}
