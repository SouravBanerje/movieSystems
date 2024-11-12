package com.infosys.demo.bean;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Movie {
	
	@Id
	private String movieId;
	private String movieName;
	private String language;
	private String genre;
	private Integer duration;
	private Double ratings;
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Movie(String movieId) {
		super();
		this.movieId = movieId;
		
	}
	public Movie(String movieId,String movieName, String language,String genre, Integer duration, Double ratings) {
		super();
		this.movieId= movieId;
		this.movieName = movieName;
		this.language = language;
		this.genre = genre;
		this.duration = duration;
		this.ratings = ratings;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Double getRatings() {
		return ratings;
	}
	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
	
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieName=" + movieName + ", language=" + language + ", genre=" + genre
				+ ", duration=" + duration + ", ratings=" + ratings + "]";
	}
	
	
	/*
	 @Override
	public String generateMovieId() {
		String val=repository.getLastId();
		long id=0L;
		if(val==null)
			id=100001L;
		else {
			id=Integer.parseInt(val.substring(1));
			id++;
		}
		String newId="M"+id;  	
		return newId;
	}

	 */
	
	
	
}
