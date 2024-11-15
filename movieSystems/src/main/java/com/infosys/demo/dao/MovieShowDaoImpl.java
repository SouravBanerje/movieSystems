package com.infosys.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.demo.bean.MovieShow;
import com.infosys.demo.bean.MovieShowEmbed;

@Service
@Repository
public class MovieShowDaoImpl implements MovieShowDao {

	@Autowired
	private MovieShowRepository repository;
	
	@Override
	public void save(MovieShow movieShow) {
		repository.save(movieShow);

	}

	
	@Override
	public List<MovieShow> findAll() {
		return repository.findAll();
	}


	@Override
	public List<MovieShowEmbed> getAllIds() {
		return repository.getAllIds();
	}

	
	@Override
	public void deleteMovieShowById(MovieShowEmbed id) {
		repository.deleteById(id);
		
	}

	//10-11
	@Override
	public List<MovieShow> findByMovieId(String movieId) {
		return repository.findByMovieId(movieId);
	}


	@Override
	public boolean existsById(MovieShowEmbed id) {
		// TODO Auto-generated method stub
		return repository.existsById(id);
	}


	@Override
	public void deleteMovieShowByMovieIdAndShowId(String movieId, Integer showTimeId) {
		repository.deleteMovieShowByMovieIdAndShowTimeId(movieId, showTimeId);
		
	}


	/*
	 * @Override public void updateMovieShowById(MovieShowEmbed id, Integer
	 * premierSeatNumber, Integer royalSeatNumber) { MovieShow movieShow =
	 * repository.findById(id).orElse(null);
	 * 
	 * if (movieShow != null) { // Update the seat numbers
	 * movieShow.setPremierSeatNumber(premierSeatNumber);
	 * movieShow.setRoyalSeatNumber(royalSeatNumber);
	 * 
	 * // Save the updated movie show repository.save(movieShow);
	 * 
	 * }
	 * 
	 * }
	 */
	
	
	
}
