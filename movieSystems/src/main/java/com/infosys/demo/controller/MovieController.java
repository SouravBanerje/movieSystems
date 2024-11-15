package com.infosys.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.infosys.demo.bean.Movie;
import com.infosys.demo.bean.MovieShow;
import com.infosys.demo.bean.MovieShowDTO;
import com.infosys.demo.bean.MovieShowEmbed;
import com.infosys.demo.bean.MovieUser;
import com.infosys.demo.bean.ShowTime;
import com.infosys.demo.dao.MovieDao;
import com.infosys.demo.dao.MovieShowDao;
import com.infosys.demo.dao.ShowTimeDao;
import com.infosys.demo.service.ShowTimeService;

@RestController
public class MovieController {

	@Autowired
	private ShowTimeDao showTimeDao;
	@Autowired
	private MovieDao movieDao;

	@Autowired
	private ShowTimeService showTimeService;

	@Autowired
	private MovieShowDao movieShowDao;

	@GetMapping("/addShowTime")
	public ModelAndView showTimeEntryPage() {
		Integer newId = showTimeDao.generateShowTimeId();
		ShowTime showTime = new ShowTime(newId);
		ModelAndView mv = new ModelAndView("showTimeEntryPage");
		mv.addObject("ShowTimeRecord", showTime);
		return mv;
	}

	@PostMapping("/addShowTime")
	public ModelAndView saveShowTimeEntryPage(@ModelAttribute("ShowTimeRecord") ShowTime showTime) {
		Double royalPrice = showTimeService.createRoyalPrice(showTime.getShowTimePremierPrice());
		showTime.setShowTimeRoyalPrice(royalPrice);
		showTimeDao.save(showTime);
		return new ModelAndView("redirect:/index");

	}

	@GetMapping("/showTimeReport")
	public ModelAndView showTimeReportPage() {
		List<ShowTime> showTimeList = showTimeDao.findAll();
		ModelAndView mv = new ModelAndView("showTimeReportPage");
		mv.addObject("showTimeList", showTimeList);
		return mv;
	}

	@GetMapping("/showTimeDeletion/{id}")
	public ModelAndView deleteShowTime(@PathVariable Integer id) {
		showTimeDao.deleteShowTimeById(id);
		return new ModelAndView("redirect:/showTimeReport");
	}

	@GetMapping("/showTimeUpdation/{id}")
	public ModelAndView getShowTimeForUpdate(@PathVariable Integer id) {
		ShowTime showTime = showTimeDao.findById(id);
		ModelAndView mv = new ModelAndView("showTimeUpdationPage");
		mv.addObject("showTime", showTime);
		return mv;
	}

	@PostMapping("/showTimeUpdation/{id}")
	public ModelAndView updateShowTime(@PathVariable Integer id, @ModelAttribute ShowTime showTime) {
	    
	    Double royalPrice = showTimeService.createRoyalPrice(showTime.getShowTimePremierPrice());
	    showTime.setShowTimeRoyalPrice(royalPrice);

	    showTimeDao.updateShowTimeById(id, showTime.getShowTimeName(), showTime.getShowTimeRoyalPrice(), showTime.getShowTimePremierPrice());

	    return new ModelAndView("redirect:/showTimeReport");
	}


	@GetMapping("/addMovie")
	public ModelAndView showMovieEntryPage() {
		String newId = movieDao.generateMovieId();
		Movie movie = new Movie(newId);
		List<Integer> showTimeList = showTimeDao.getAllIds();
		ModelAndView mv = new ModelAndView("movieEntryPage");
		mv.addObject("movieRecord", movie);
		mv.addObject("showTimeList", showTimeList);
		return mv;
	}

	@PostMapping("/addMovie")
	public ModelAndView saveMovieEntryPage(@ModelAttribute("movieRecord") Movie movie, HttpServletRequest request) {

		String movieId = movie.getMovieId();
		movieDao.save(movie); // Save the Movie entity

		// Loop to process show times and seat types for this movie
		for (int i = 1; i <= 6; i++) {
			String premierSeat = request.getParameter("premier" + i);

			if (premierSeat != null && !premierSeat.equals("---")) {
				Integer showId = Integer.parseInt(request.getParameter("show" + i));
				Integer premier = Integer.parseInt(premierSeat);
				Integer royal = Integer.parseInt(request.getParameter("royal" + i));

				MovieShowEmbed embed = new MovieShowEmbed(movieId, showId);

				MovieShow movieShow = new MovieShow(embed, royal, premier);

				movieShowDao.save(movieShow);
			}
		}

		return new ModelAndView("redirect:/index");

	}

	@GetMapping("/movieReport")
	public ModelAndView showMovieReportPage() {
		List<ShowTime> showTimeList = showTimeDao.findAll();
		Map<Integer, String> showMap = new HashMap<>();
		for (ShowTime showTime : showTimeList) {
			showMap.put(showTime.getShowTimeId(), showTime.getShowTimeName());
		}
		List<Movie> movieList = movieDao.findAll();
		List<MovieShow> movieShowList = movieShowDao.findAll();
		Map<String, List<MovieShowDTO>> movieMap = new HashMap<>();

		for (Movie movie : movieList) {

			List<MovieShowDTO> dtoList = new ArrayList<>();

			String movieId = movie.getMovieId();

			for (MovieShow movieShow : movieShowList) {

				MovieShowEmbed id = movieShow.getEmbeddedId();

				if (movieId.equals(id.getMovieId())) {

					String showTimeName = showMap.get(id.getShowTimeId());

					Integer royalSeat = movieShow.getRoyalSeatNumber();

					Integer premierSeat = movieShow.getPremierSeatNumber();

					Integer royalBooked = movieShow.getRoyalBooked();

					Integer premierBooked = movieShow.getPremierBooked();

					MovieShowDTO movieShowDto = new MovieShowDTO(showTimeName, movieId, royalSeat, premierSeat,
							royalBooked, premierBooked);

					dtoList.add(movieShowDto);

				} // end of if

			} // end of inner loop

			movieMap.put(movieId, dtoList);

		}
		System.out.println(movieMap);
		ModelAndView mv = new ModelAndView("movieReportPage");
		mv.addObject("movieList", movieList);
		mv.addObject("movieMap", movieMap);
		return mv;

	}

	
	
	//10 -11
	@GetMapping("/movieDeletion")
	public ModelAndView showMovieDeletionPage() {
		List<ShowTime> showTimeList = showTimeDao.findAll();
		Map<Integer, String> showMap = new HashMap<>();
		for (ShowTime showTime : showTimeList) {
			showMap.put(showTime.getShowTimeId(), showTime.getShowTimeName());
		}
		List<Movie> movieList = movieDao.findAll();
		List<MovieShow> movieShowList = movieShowDao.findAll();
		Map<String, List<MovieShowDTO>> movieMap = new HashMap<>();

		for (Movie movie : movieList) {

			List<MovieShowDTO> dtoList = new ArrayList<>();

			String movieId = movie.getMovieId();

			for (MovieShow movieShow : movieShowList) {

				MovieShowEmbed id = movieShow.getEmbeddedId();

				if (movieId.equals(id.getMovieId())) {

					String showTimeName = showMap.get(id.getShowTimeId());

					Integer royalSeat = movieShow.getRoyalSeatNumber();

					Integer premierSeat = movieShow.getPremierSeatNumber();

					Integer royalBooked = movieShow.getRoyalBooked();

					Integer premierBooked = movieShow.getPremierBooked();

					MovieShowDTO movieShowDto = new MovieShowDTO(showTimeName, movieId, royalSeat, premierSeat,
							royalBooked, premierBooked);

					dtoList.add(movieShowDto);

				} // end of if

			} // end of inner loop

			movieMap.put(movieId, dtoList);

		}
		System.out.println(movieMap);
		ModelAndView mv = new ModelAndView("movieDeleteViewPage");
		mv.addObject("movieList", movieList);
		mv.addObject("movieMap", movieMap);
		return mv;

	}
	
	
	//10-11
	@GetMapping("/movieDeletion1/{id}")
	public ModelAndView movieDeletion1(@PathVariable String id) {
		List<MovieShowEmbed> embedList = movieShowDao.getAllIds();

		// List<Integer> showIdList = new ArrayList<Integer>();
		for (MovieShowEmbed mse : embedList) {
			if (id.equals(mse.getMovieId())) {
				MovieShowEmbed mse2 = new MovieShowEmbed(id, mse.getShowTimeId());
				movieShowDao.deleteMovieShowById(mse2);
			}
		}
		movieDao.deleteMovieById(id);
		return new ModelAndView("redirect:/movieDeletion");
	}
	
	
	
	@GetMapping("/movieDeletion/{id}")
	public ModelAndView movieDeletion(@PathVariable String id) {
		List<MovieShowEmbed> embedList = movieShowDao.getAllIds();

		// List<Integer> showIdList = new ArrayList<Integer>();
		for (MovieShowEmbed mse : embedList) {
			if (id.equals(mse.getMovieId())) {
				MovieShowEmbed mse2 = new MovieShowEmbed(id, mse.getShowTimeId());
				movieShowDao.deleteMovieShowById(mse2);
			}
		}
		movieDao.deleteMovieById(id);
		return new ModelAndView("redirect:/movieReport");
	}

	// working on UPDATE -- link

	@GetMapping("/movieUpdate")
	public ModelAndView showMovieUpdatePage() {
		List<ShowTime> showTimeList = showTimeDao.findAll();
		Map<Integer, String> showMap = new HashMap<>();
		for (ShowTime showTime : showTimeList) {
			showMap.put(showTime.getShowTimeId(), showTime.getShowTimeName());
		}
		List<Movie> movieList = movieDao.findAll();
		List<MovieShow> movieShowList = movieShowDao.findAll();
		Map<String, List<MovieShowDTO>> movieMap = new HashMap<>();

		for (Movie movie : movieList) {

			List<MovieShowDTO> dtoList = new ArrayList<>();

			String movieId = movie.getMovieId();

			for (MovieShow movieShow : movieShowList) {

				MovieShowEmbed id = movieShow.getEmbeddedId();

				if (movieId.equals(id.getMovieId())) {

					String showTimeName = showMap.get(id.getShowTimeId());

					Integer royalSeat = movieShow.getRoyalSeatNumber();

					Integer premierSeat = movieShow.getPremierSeatNumber();

					Integer royalBooked = movieShow.getRoyalBooked();

					Integer premierBooked = movieShow.getPremierBooked();

					MovieShowDTO movieShowDto = new MovieShowDTO(showTimeName, movieId, royalSeat, premierSeat,
							royalBooked, premierBooked);

					dtoList.add(movieShowDto);

				} // end of if

			} // end of inner loop

			movieMap.put(movieId, dtoList);

		}
		System.out.println(movieMap);
		ModelAndView mv = new ModelAndView("movieUpdateViewPage");
		mv.addObject("movieList", movieList);
		mv.addObject("movieMap", movieMap);
		return mv;

	}

	/*
	 * @GetMapping("/movieUpdate/{movieId}") public ModelAndView
	 * showMovieUpdatePage(@PathVariable("movieId") String movieId) {
	 * 
	 * Movie movie = movieDao.findById(movieId);
	 * 
	 * List<Integer> showTimeList = showTimeDao.getAllIds();
	 * 
	 * ModelAndView mv = new ModelAndView("movieUpdatePage");
	 * mv.addObject("movieRecord", movie); mv.addObject("showTimeList",
	 * showTimeList); return mv; }
	 */

	//10-11
	@GetMapping("/movieUpdate1/{movieId}")
	public ModelAndView showMovieUpdatePage1(@PathVariable("movieId") String movieId) {

		Movie movie = movieDao.findById(movieId);
		List<Integer> showTimeList = showTimeDao.getAllIds();

		List<MovieShow> movieShows = movieShowDao.findByMovieId(movieId);

		ModelAndView mv = new ModelAndView("movieUpdatePage1");
		mv.addObject("movieRecord", movie);
		mv.addObject("showTimeList", showTimeList);
		mv.addObject("movieShows", movieShows); // Add movieShows to the model
		return mv;
	}
	
	
	
	
	// 10-11
	@GetMapping("/movieUpdate/{movieId}")
	public ModelAndView showMovieUpdatePage(@PathVariable("movieId") String movieId) {

		Movie movie = movieDao.findById(movieId);
		List<Integer> showTimeList = showTimeDao.getAllIds();

		List<MovieShow> movieShows = movieShowDao.findByMovieId(movieId);

		ModelAndView mv = new ModelAndView("movieUpdatePage");
		mv.addObject("movieRecord", movie);
		mv.addObject("showTimeList", showTimeList);
		mv.addObject("movieShows", movieShows); // Add movieShows to the model
		return mv;
	}

	
	
	/*
	 * @PostMapping("/movieUpdate/{movieId}") public ModelAndView saveMovieUpdate(
	 * 
	 * @ModelAttribute("movieRecord") Movie movie, HttpServletRequest request,
	 * 
	 * @PathVariable("movieId") String movieId) {
	 * 
	 * // Update the movie record movieDao.updateMovieById(movieId,
	 * movie.getMovieName(),movie.getLanguage(),movie.getGenre(),movie.getDuration()
	 * ,movie.getRatings());
	 * 
	 * 
	 * // Remove previous showtimes associated with this movie List<MovieShowEmbed>
	 * embedList = movieShowDao.getAllIds();
	 * 
	 * //List<Integer> showIdList = new ArrayList<Integer>(); for(MovieShowEmbed mse
	 * : embedList) { if(movieId.equals(mse.getMovieId())) { MovieShowEmbed mse2 =
	 * new MovieShowEmbed(movieId,mse.getShowTimeId());
	 * movieShowDao.deleteMovieShowById(mse2); } }
	 * 
	 * 
	 * 
	 * for (int i = 1; i <= 6; i++) { String premierSeat =
	 * request.getParameter("premier" + i);
	 * 
	 * 
	 * Integer showId = Integer.parseInt(request.getParameter("show" + i)); Integer
	 * premier = Integer.parseInt(premierSeat); Integer royal =
	 * Integer.parseInt(request.getParameter("royal" + i));
	 * 
	 * MovieShowEmbed embed = new MovieShowEmbed(movieId, showId); MovieShow
	 * movieShow = new MovieShow(embed, royal, premier);
	 * movieShowDao.save(movieShow); }
	 * 
	 * 
	 * 
	 * return new ModelAndView("redirect:/movieReport"); }
	 */

	
	
	
	//11-11
	@PostMapping("/movieUpdate1/{movieId}")
	public ModelAndView saveMovieUpdate1(
	        @ModelAttribute("movieRecord") Movie movie,
	        HttpServletRequest request,
	        @PathVariable("movieId") String movieId) {

	    // Update the main movie record (this part works as expected)
	    movieDao.updateMovieById(movieId, movie.getMovieName(), movie.getLanguage(),
	            movie.getGenre(), movie.getDuration(), movie.getRatings());

	    // Loop through each show (1 to 6)
	    for (int i = 1; i <= 6; i++) {
	        String premierSeat = request.getParameter("premier" + i);
	        String royalSeat = request.getParameter("royal" + i);
	        String showIdStr = request.getParameter("show" + i);

	        Integer showId = null;
	        Integer premier = null;
	        Integer royal = null;

	        // Parse showId only if it's not empty
	        if (showIdStr != null && !showIdStr.isEmpty()) {
	            try {
	                showId = Integer.parseInt(showIdStr);
	            } catch (NumberFormatException e) {
	                showId = null;
	            }
	        }

	        // Skip if showId is invalid
	        if (showId == null) {
	            continue;
	        }

	        // Check if both premier and royal seats are empty (indicating deletion)
	        boolean isPremierEmpty = (premierSeat == null || premierSeat.isEmpty());
	        boolean isRoyalEmpty = (royalSeat == null || royalSeat.isEmpty());

	        // If both premier and royal seats are empty, delete the movie show record
	        if (isPremierEmpty && isRoyalEmpty) {
	            MovieShowEmbed embed = new MovieShowEmbed(movieId, showId);
	            if (movieShowDao.existsById(embed)) {
	                movieShowDao.deleteMovieShowById(embed);
	                System.out.println("Deleted movie show with showId: " + showId);
	            }
	        } else {
	            // Parse premier and royal seat values if they're not empty
	            if (!isPremierEmpty) {
	                try {
	                    premier = Integer.parseInt(premierSeat);
	                } catch (NumberFormatException e) {
	                    premier = 0; // Default to 0 if parsing fails
	                }
	            }

	            if (!isRoyalEmpty) {
	                try {
	                    royal = Integer.parseInt(royalSeat);
	                } catch (NumberFormatException e) {
	                    royal = 0; // Default to 0 if parsing fails
	                }
	            }

	            // Now we need to handle updating or inserting the show entry
	            MovieShowEmbed embed = new MovieShowEmbed(movieId, showId);
	            MovieShow movieShow = new MovieShow(embed, royal, premier);

	            // First, check if the old show exists (showId = 3)
	            MovieShowEmbed oldEmbed = new MovieShowEmbed(movieId, showId);

	            // Delete the old show first if it exists
	            if (movieShowDao.existsById(oldEmbed)) {
	                movieShowDao.deleteMovieShowById(oldEmbed);
	                System.out.println("Deleted old movie show with showId: " + showId);
	            }

	            // Save the updated/new show with the new showId (showId = 4)
	            movieShowDao.save(movieShow);
	            System.out.println("Saved updated/new movie show with showId: " + showId);
	        }
	    }

	    // Redirect back to movie update page
	    return new ModelAndView("redirect:/movieUpdate");
	}






	//10-11
	@PostMapping("/movieUpdate/{movieId}")
	public ModelAndView saveMovieUpdate(
	        @ModelAttribute("movieRecord") Movie movie,
	        HttpServletRequest request, 
	        @PathVariable("movieId") String movieId) {

		 movieDao.updateMovieById(movieId, movie.getMovieName(), movie.getLanguage(),
		            movie.getGenre(), movie.getDuration(), movie.getRatings());

		    // Loop through each show (1 to 6)
		    for (int i = 1; i <= 6; i++) {
		        String premierSeat = request.getParameter("premier" + i);
		        String royalSeat = request.getParameter("royal" + i);
		        String showIdStr = request.getParameter("show" + i);

		        Integer showId = null;
		        Integer premier = null;
		        Integer royal = null;

		        // Parse showId
		        if (showIdStr != null && !showIdStr.isEmpty()) {
		            try {
		                showId = Integer.parseInt(showIdStr);
		            } catch (NumberFormatException e) {
		                showId = null;
		            }
		        }

		        // Check if seats are empty
		        boolean isPremierEmpty = (premierSeat == null || premierSeat.isEmpty());
		        boolean isRoyalEmpty = (royalSeat == null || royalSeat.isEmpty());

		        // If both premier and royal seats are empty and showId is valid, delete the movie show record
		        if (isPremierEmpty && isRoyalEmpty && showId != null) {
		            MovieShowEmbed embed = new MovieShowEmbed(movieId, showId);
		            
		            if (movieShowDao.existsById(embed)) {  // Check if record exists before deletion
		                movieShowDao.deleteMovieShowById(embed); // Delete the movie show
		                System.out.println("Deleted movie show with showId: " + showId);  // Debugging output
		            }
		        } else {
		            // Parse the seats if they are not empty
		            if (!isPremierEmpty) {
		                try {
		                    premier = Integer.parseInt(premierSeat);
		                } catch (NumberFormatException e) {
		                    premier = 0;  // Default to 0 if parsing fails
		                }
		            }

		            if (!isRoyalEmpty) {
		                try {
		                    royal = Integer.parseInt(royalSeat);
		                } catch (NumberFormatException e) {
		                    royal = 0;  // Default to 0 if parsing fails
		                }
		            }

		            // If showId, premier, and royal are valid, save or update the movie show record
		            if (showId != null && showId > 0 && premier != null && royal != null) {
		                MovieShowEmbed embed = new MovieShowEmbed(movieId, showId);
		                MovieShow movieShow = new MovieShow(embed, royal, premier);
		                movieShowDao.save(movieShow); // Save or update the record
		                System.out.println("Saved movie show with showId: " + showId + ", Premier: " + premier + ", Royal: " + royal);  // Debugging output
		            }
		        }
		    }

		    return new ModelAndView("redirect:/movieReport"); 
	}

	
	
	//11-11
	@GetMapping("/movieLanguage")
    public ModelAndView showLanguageSelectionPage() {
        // Return the view for language selection page
        return new ModelAndView("movieLanguage");
    }

	@GetMapping("/movieLanguageResults")
	public ModelAndView findMoviesByLanguage(@RequestParam("language") String language) {
	    
	    List<ShowTime> showTimeList = showTimeDao.findAll();
	    
	    
	    Map<Integer, String> showMap = new HashMap<>();
	    for (ShowTime showTime : showTimeList) {
	        showMap.put(showTime.getShowTimeId(), showTime.getShowTimeName());
	    }

	    
	    List<Movie> movieList = movieDao.getLanguagewiseMovieList(language);
	    List<MovieShow> movieShowList = movieShowDao.findAll();

	
	    Map<String, List<MovieShowDTO>> movieMap = new HashMap<>();
	    
	    // Group movie shows by movieId 
	    for (MovieShow movieShow : movieShowList) {
	        String movieId = movieShow.getEmbeddedId().getMovieId();
	        String showTimeName = showMap.get(movieShow.getEmbeddedId().getShowTimeId());

	        Integer royalSeat = movieShow.getRoyalSeatNumber();
	        Integer premierSeat = movieShow.getPremierSeatNumber();
	        Integer royalBooked = movieShow.getRoyalBooked();
	        Integer premierBooked = movieShow.getPremierBooked();

	        MovieShowDTO movieShowDto = new MovieShowDTO(showTimeName, movieId, royalSeat, premierSeat, royalBooked, premierBooked);

	        // Add the DTO to the map for the corresponding movieId
	        movieMap.computeIfAbsent(movieId, k -> new ArrayList<>()).add(movieShowDto);
	    }

	    // Create a ModelAndView and add attributes for the view
	    ModelAndView mv = new ModelAndView("moviesByLanguage");
	    mv.addObject("movieList", movieList);
	    mv.addObject("movieMap", movieMap);
	    
	    return mv;
	}

	
	//11-11
	@GetMapping("/movieReport1")
	public ModelAndView showMovieReportPage1() {
		List<ShowTime> showTimeList = showTimeDao.findAll();
		Map<Integer, String> showMap = new HashMap<>();
		for (ShowTime showTime : showTimeList) {
			showMap.put(showTime.getShowTimeId(), showTime.getShowTimeName());
		}
		List<Movie> movieList = movieDao.findAll();
		List<MovieShow> movieShowList = movieShowDao.findAll();
		Map<String, List<MovieShowDTO>> movieMap = new HashMap<>();

		for (Movie movie : movieList) {

			List<MovieShowDTO> dtoList = new ArrayList<>();

			String movieId = movie.getMovieId();

			for (MovieShow movieShow : movieShowList) {

				MovieShowEmbed id = movieShow.getEmbeddedId();

				if (movieId.equals(id.getMovieId())) {

					String showTimeName = showMap.get(id.getShowTimeId());

					Integer royalSeat = movieShow.getRoyalSeatNumber();

					Integer premierSeat = movieShow.getPremierSeatNumber();

					Integer royalBooked = movieShow.getRoyalBooked();

					Integer premierBooked = movieShow.getPremierBooked();

					MovieShowDTO movieShowDto = new MovieShowDTO(showTimeName, movieId, royalSeat, premierSeat,
							royalBooked, premierBooked);

					dtoList.add(movieShowDto);

				} // end of if

			} // end of inner loop

			movieMap.put(movieId, dtoList);

		}
		System.out.println(movieMap);
		ModelAndView mv = new ModelAndView("movieReportCustomer");
		mv.addObject("movieList", movieList);
		mv.addObject("movieMap", movieMap);
		return mv;

	}


}
