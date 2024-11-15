package com.infosys.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.demo.bean.MovieShow;
import com.infosys.demo.bean.MovieShowEmbed;



public interface MovieShowRepository extends JpaRepository<MovieShow,MovieShowEmbed> {
		
	@Query("select embeddedId from MovieShow")
	public List<MovieShowEmbed> getAllIds();
	
	//10-11
	@Query("SELECT m FROM MovieShow m WHERE m.embeddedId.movieId = :movieId")
    public List<MovieShow> findByMovieId(String movieId);
	
	  	@Modifying
	    @Transactional
	    @Query("DELETE FROM MovieShow ms WHERE ms.embeddedId.movieId = :movieId AND ms.embeddedId.showTimeId = :showTimeId")
	    void deleteMovieShowByMovieIdAndShowTimeId(@Param("movieId") String movieId, @Param("showTimeId") Integer showTimeId);
}
