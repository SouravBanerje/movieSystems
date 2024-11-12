package com.infosys.demo.bean;

public class MovieShowDTO {
	
	private String showTimeName;
	private String movieId;
	private Integer royalSeatNumber;
	private Integer premierSeatNumber;
	private Integer royalBooked;
	private Integer premierBooked;
	public MovieShowDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieShowDTO(String showTimeName, String movieId, Integer royalSeatNumber, Integer premierSeatNumber,
			Integer royalBooked, Integer premierBooked) {
		super();
		this.showTimeName = showTimeName;
		this.movieId = movieId;
		this.royalSeatNumber = royalSeatNumber;
		this.premierSeatNumber = premierSeatNumber;
		this.royalBooked = royalBooked;
		this.premierBooked = premierBooked;
	}
	public String getShowTimeName() {
		return showTimeName;
	}
	public void setShowTimeName(String showTimeName) {
		this.showTimeName = showTimeName;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Integer getRoyalSeatNumber() {
		return royalSeatNumber;
	}
	public void setRoyalSeatNumber(Integer royalSeatNumber) {
		this.royalSeatNumber = royalSeatNumber;
	}
	public Integer getPremierSeatNumber() {
		return premierSeatNumber;
	}
	public void setPremierSeatNumber(Integer premierSeatNumber) {
		this.premierSeatNumber = premierSeatNumber;
	}
	public Integer getRoyalBooked() {
		return royalBooked;
	}
	public void setRoyalBooked(Integer royalBooked) {
		this.royalBooked = royalBooked;
	}
	public Integer getPremierBooked() {
		return premierBooked;
	}
	public void setPremierBooked(Integer premierBooked) {
		this.premierBooked = premierBooked;
	}
	@Override
	public String toString() {
		return "MovieShowDTO [showTimeName=" + showTimeName + ", movieId=" + movieId + ", royalSeatNumber="
				+ royalSeatNumber + ", premierSeatNumber=" + premierSeatNumber + ", royalBooked=" + royalBooked
				+ ", premierBooked=" + premierBooked + "]";
	}
	
	
}
