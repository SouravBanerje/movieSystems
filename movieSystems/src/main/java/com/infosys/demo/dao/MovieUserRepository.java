package com.infosys.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.demo.bean.MovieUser;

public interface MovieUserRepository extends JpaRepository<MovieUser, String>{

}
