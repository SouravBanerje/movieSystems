package com.infosys.demo.dao;

import java.util.List;

import com.infosys.demo.bean.MovieShow;
import com.infosys.demo.bean.MovieShowEmbed;

public interface MovieShowDao {
	
	public void save(MovieShow movieShow);
	
	public List<MovieShow> findAll();
	public List<MovieShowEmbed> getAllIds();
	public void deleteMovieShowById(MovieShowEmbed id);
	public boolean existsById(MovieShowEmbed id);

	
	 // New method to find MovieShow records by movieId
    public List<MovieShow> findByMovieId(String movieId);
	/*
	public void updateMovieShowById(MovieShowEmbed id,
			Integer premierSeatNumber,
			Integer royalSeatNumber);
			*/
}
