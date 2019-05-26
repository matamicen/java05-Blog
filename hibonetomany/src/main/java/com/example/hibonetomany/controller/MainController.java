package com.example.hibonetomany.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hibonetomany.model.InputPostComment;
import com.example.hibonetomany.model.InputPostPost;
import com.example.hibonetomany.model.Post;
import com.example.hibonetomany.model.PostComment;
import com.example.hibonetomany.model.Users;
import com.example.hibonetomany.repository.DaoPost;
import com.example.hibonetomany.repository.DaoPostComment;
import com.example.hibonetomany.repository.DaoUsers;

@RestController
@RequestMapping({"/social"})
public class MainController {
	
	@Autowired
	DaoUsers daousers;
	
	@Autowired
	DaoPost daopost;
	
	@Autowired
	DaoPostComment daopostcomment;
	

	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = {"/users"})	
	public ResponseEntity<Object> getAllUsers() {
		
		
		List<Users> usersFounds = daousers.findAll();
		
		//
		
		JSONArray json_array= new JSONArray();
		
		if (usersFounds.size()>0) {	
			 
			
			 for(Users us: usersFounds) {
				 System.out.println("book name:"+us.getName());
				 JSONObject aux = new JSONObject();
				 aux.put("id",us.getId());
				 aux.put("name", us.getName());
				 aux.put("fechaNacimiento", us.getFechanac());
				 aux.put("avatar", us.getAvatar());
				 json_array.put(aux);
				
		     }
				 
		 }
				 
		 JSONObject obj = new JSONObject();
		 


	     obj.put("error", 0);
	     obj.put("results", json_array);

	      

	     	return ResponseEntity.ok().body(obj.toString());
			
		
		
		}
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = {"/posts"})	
	public ResponseEntity<Object> getAllPosts() {
		
		
		List<Post> postsFounds = daopost.findAll();
		
		//
		
		JSONArray json_array= new JSONArray();
		
		if (postsFounds.size()>0) {	
			 
			
			 for(Post post: postsFounds) {
				
				 JSONObject aux = new JSONObject();
				 
				 aux.put("id",post.getId());
				 aux.put("title", post.getTitle());
				 aux.put("review", post.getReview());
				 aux.put("username", post.getUser().getName());
				 aux.put("avatar", post.getUser().getAvatar());
				 json_array.put(aux);
				
		     }
				 
		 }
				 
		 JSONObject obj = new JSONObject();
		 


	     obj.put("error", 0);
	     obj.put("results", json_array);

	      

	     	return ResponseEntity.ok().body(obj.toString());
			
		
		
		}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = {"/comment/{idpost}"})	
	public ResponseEntity<Object> getCommentFromPost(@PathVariable Long idpost) {
		
		 System.out.println("idpost:"+idpost);
		 Post pos = daopost.findById(idpost).orElse(null);
		 
		// JSONArray json_array1  = new JSONArray();
		 JSONObject postjson = new JSONObject();
		 
		 postjson.put("title", pos.getTitle());
		 postjson.put("review", pos.getReview());
		 postjson.put("usuario", pos.getUser().getName());
		 postjson.put("avatar", pos.getUser().getAvatar());
		 postjson.put("id", pos.getId());
		
			 
		 
		 List<PostComment> commentsFound = daopostcomment.findByPost(pos);
		
		
		
		JSONArray json_array2= new JSONArray();
		
		if (commentsFound.size()>0) {	
			 
			
			 for(PostComment com: commentsFound) {
				 System.out.println("comment:"+com.getComment());
				
				 JSONObject aux = new JSONObject();
				 
				
				 
				 aux.put("comment", com.getComment());
				 aux.put("usuariocomment", com.getUser().getName());
				 aux.put("usuarioavatar", com.getUser().getAvatar());
				 json_array2.put(aux);
				
		     }
				 
		 }
		
		     
		 JSONObject obj = new JSONObject();
		 


	     obj.put("error", 0);
	     obj.put("post", postjson);
	     obj.put("results", json_array2);

	      

	     	return ResponseEntity.ok().body(obj.toString());
			
		
		
		}
	
	
	@PostMapping(path = {"/users"})
	public Users create(@RequestBody Users user){

		
	   
	    return daousers.save(user);
	    
	}
	
	@PostMapping(path = {"/post"})
	public ResponseEntity<Object> createPost(@RequestBody InputPostPost post){
		
		System.out.println("entro al post de POST");
		System.out.println(post.getIdUser());
		System.out.println(post.getInputPost().getTitle());
		System.out.println(post.getInputPost().getReview());
		
	
		//   long id = Long.valueOf(post.getIdUser());
		   
		   Users us1 = daousers.findById(post.getIdUser()).orElse(null);
		   Post pos = new Post();
		   
		   if (us1!=null) {
				 System.out.println("encontro USER!!!!!!");
				 
			    pos.setUser(us1);
	        	pos.setTitle(post.getInputPost().getTitle());
	        	pos.setReview(post.getInputPost().getReview());
	        	
	        	Post posteo = daopost.save(pos);
			        	
			       	 JSONObject obj = new JSONObject();
					 
		
				     obj.put("error", 0);
				     obj.put("message",  posteo.getId());

				
				
				return ResponseEntity.ok().body(obj.toString());
				}else { 
					JSONObject obj = new JSONObject();
					 
					
				     obj.put("error", 1);
				     obj.put("message", "User not found");
					
					   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
					   }
		   

	    
	}
	
	@PostMapping(path = {"/comment"})
	public ResponseEntity<Object> createComment(@RequestBody InputPostComment comment){
		
		System.out.println("entro al Comment");
		System.out.println(comment.getIdUser());
		System.out.println(comment.getIdPost());
		System.out.println(comment.getComment());
		
	   
		   Users us1 = daousers.findById(comment.getIdUser()).orElse(null);
		   Post pos = daopost.findById(comment.getIdPost()).orElse(null);
		   
	   
		   if (us1!=null && pos!=null) {
				 System.out.println("encontro USER!!!!!!");
				
				PostComment postcomment = new PostComment() ;
				
				postcomment.setUser(us1);
				postcomment.setPost(pos);
				postcomment.setComment(comment.getComment());
				
				PostComment commenResult = daopostcomment.save(postcomment);
				
			
			        	
			       	 JSONObject obj = new JSONObject();
					 
		
				     obj.put("error", 0);
				     obj.put("message",  commenResult.getId());

				
				
				return ResponseEntity.ok().body(obj.toString());
				}else { 
					JSONObject obj = new JSONObject();
					 
					
				     obj.put("error", 1);
				     obj.put("message", "User or Post not found");
					
					   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(obj.toString());
					   }
		   

	    
	}
	
	
}
