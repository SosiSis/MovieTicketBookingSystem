package com.itsc.movie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.movie.convertor.MovieConvertor;
import com.itsc.movie.entities.Movie;
import com.itsc.movie.exceptions.MovieAlreadyExist;
import com.itsc.movie.repositories.MovieRepository;
import com.itsc.movie.request.MovieRequest;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public String addMovie(MovieRequest movieRequest) {
		Movie movieByName = movieRepository.findByMovieName(movieRequest.getMovieName());
		
		if (movieByName != null && movieByName.getLanguage().equals(movieRequest.getLanguage())) {
			throw new MovieAlreadyExist();
		}
		
		Movie movie = MovieConvertor.movieDtoToMovie(movieRequest);
		
		movieRepository.save(movie);
		return "The movie has been added successfully";
	}

}
