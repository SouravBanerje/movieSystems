package com.infosys.demo.dao;

import java.util.List;

import com.infosys.demo.bean.Movie;
import com.infosys.demo.bean.MovieShow;
import com.infosys.demo.bean.MovieShowEmbed;

public interface MovieDao {
		
	public void save(Movie movie);
	public String generateMovieId();
	public Movie findById(String id);
	public List<Movie> findAll();
	public void deleteMovieById(String id);
	public List<Movie> getLanguagewiseMovieList (String language);
	public void updateMovieById(String id,
			 String NewmovieName,
			 String Newlanguage,
			 String Newgenre,
			 Integer Newduration,
			 Double Newratings);
	//11-11
	List<MovieShow> findShowTimesByMovieId(String movieId);
}
