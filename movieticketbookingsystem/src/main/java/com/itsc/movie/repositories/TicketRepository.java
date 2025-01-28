package com.itsc.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsc.movie.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
