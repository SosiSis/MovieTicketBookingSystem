package com.itsc.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsc.movie.entities.Theater;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByAddress(String address);
}
