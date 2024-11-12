package com.infosys.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.demo.bean.ShowTime;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer>{
	
	@Query("select max(showTimeId) from ShowTime")
	public Integer getLastId();
	
	@Query("select showTimeId from ShowTime")
	List<Integer> getAllShowTimeIds();

}
