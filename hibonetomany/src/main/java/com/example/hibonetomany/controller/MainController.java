package com.example.hibonetomany.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.hibonetomany.model.Users;
import com.example.hibonetomany.repository.DaoUsers;

@RestController
@RequestMapping({"/social"})
public class MainController {
	
	@Autowired
	DaoUsers daousers;
	
	
	@GetMapping(path = {"/users"})	
	public ResponseEntity<Object> getAllBooks() {
		
		
		List<Users> usersFounds = daousers.findAll();
		
		//
		
		JSONArray json_array= new JSONArray();
		
		if (usersFounds.size()>0) {	
			 
			
			 for(Users us: usersFounds) {
				 System.out.println("book name:"+us.getName());
				 JSONObject aux = new JSONObject();
				 aux.put("name", us.getName());
				 aux.put("fechaNacimiento", us.getFechanac());
				 json_array.put(aux);
				
		     }
				 
		 }
				 
		 JSONObject obj = new JSONObject();
		 


	     obj.put("error", 0);
	     obj.put("results", json_array);

	      

	     	return ResponseEntity.ok().body(obj.toString());
			
		
		
		}
	
	@PostMapping(path = {"/users"})
	public Users create(@RequestBody Users user){

		
	   
	    return daousers.save(user);
	    
	}
}
