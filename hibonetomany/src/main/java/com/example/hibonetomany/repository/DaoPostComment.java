package com.example.hibonetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibonetomany.model.Post;
import com.example.hibonetomany.model.PostComment;

public interface DaoPostComment extends JpaRepository<PostComment, Long> {
	
	public List<PostComment> findByPost(Post post);

}
