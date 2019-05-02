package com.example.hibonetomany.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class Users {

	
		 
		@Id
		@GeneratedValue
		private Long id;
		private String name;
		private Date fechanac;
		
		   @OneToMany(cascade = CascadeType.ALL, 
			        mappedBy = "user")
		    private List<Post> Post;
		
		   @OneToMany(cascade = CascadeType.ALL, 
			        mappedBy = "user")
			    private List<PostComment> comments;
		   
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getFechanac() {
			return fechanac;
		}
		public void setFechanac(Date fechanac) {
			this.fechanac = fechanac;
		}
		public List<Post> getPost() {
			return Post;
		}
		public void setPost(List<Post> post) {
			Post = post;
		}
		public List<PostComment> getComments() {
			return comments;
		}
		public void setComments(List<PostComment> comments) {
			this.comments = comments;
		}
		
		
	
}
