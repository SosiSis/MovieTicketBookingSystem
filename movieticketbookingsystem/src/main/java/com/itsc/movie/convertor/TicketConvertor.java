package com.itsc.movie.convertor;

import com.itsc.movie.entities.Show;
import com.itsc.movie.entities.Ticket;
import com.itsc.movie.response.TicketResponse;

public class TicketConvertor {

    public static TicketResponse returnTicket(Show show, Ticket ticket) {
        TicketResponse ticketResponseDto = TicketResponse.builder()
                .bookedSeats(ticket.getBookedSeats())
                .address(show.getTheater().getAddress())
                .theaterName(show.getTheater().getName())
                .movieName(show.getMovie().getMovieName())
                .date(show.getDate())
                .time(show.getTime())
                .totalPrice(ticket.getTotalTicketsPrice())
                .build();

        return ticketResponseDto;
    }
}
