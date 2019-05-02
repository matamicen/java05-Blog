package com.example.hibonetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibonetomany.model.PostComment;

public interface DaoPostComment extends JpaRepository<PostComment, Long> {

}
