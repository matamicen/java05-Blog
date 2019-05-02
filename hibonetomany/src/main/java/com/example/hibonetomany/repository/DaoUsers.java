package com.example.hibonetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hibonetomany.model.Users;

public interface DaoUsers extends JpaRepository<Users, Long> {

}
