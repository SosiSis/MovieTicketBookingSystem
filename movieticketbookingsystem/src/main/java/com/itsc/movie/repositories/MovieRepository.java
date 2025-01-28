package com.itsc.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsc.movie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Movie findByMovieName(String name);
}