package com.itsc.movie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsc.movie.convertor.TheaterConvertor;
import com.itsc.movie.entities.Theater;
import com.itsc.movie.entities.TheaterSeat;
import com.itsc.movie.enums.SeatType;
import com.itsc.movie.exceptions.TheaterIsExist;
import com.itsc.movie.exceptions.TheaterIsNotExist;
import com.itsc.movie.repositories.TheaterRepository;
import com.itsc.movie.request.TheaterRequest;
import com.itsc.movie.request.TheaterSeatRequest;

@Service
public class TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	public String addTheater(TheaterRequest theaterRequest) throws TheaterIsExist {
		if (theaterRepository.findByAddress(theaterRequest.getAddress()) != null) {
			throw new TheaterIsExist();
		}
		
		Theater theater = TheaterConvertor.theaterDtoToTheater(theaterRequest);

		theaterRepository.save(theater);
		return "Theater has been saved Successfully";
	}

	public String addTheaterSeat(TheaterSeatRequest entryDto) throws TheaterIsNotExist {
		if (theaterRepository.findByAddress(entryDto.getAddress()) == null) {
			throw new TheaterIsNotExist();
		}
		
		Integer noOfSeatsInRow = entryDto.getNoOfSeatInRow();
		Integer noOfPremiumSeats = entryDto.getNoOfPremiumSeat();
		Integer noOfClassicSeat = entryDto.getNoOfClassicSeat();
		String address = entryDto.getAddress();

		Theater theater = theaterRepository.findByAddress(address);

		List<TheaterSeat> seatList = theater.getTheaterSeatList();

		int counter = 1;
		int fill = 0;
		char ch = 'A';

		for (int i = 1; i <= noOfClassicSeat; i++) {
			String seatNo = Integer.toString(counter) + ch;

			ch++;
			fill++;
			if (fill == noOfSeatsInRow) {
				fill = 0;
				counter++;
				ch = 'A';
			}

			TheaterSeat theaterSeat = new TheaterSeat();
			theaterSeat.setSeatNo(seatNo);
			theaterSeat.setSeatType(SeatType.CLASSIC);
			theaterSeat.setTheater(theater);
			seatList.add(theaterSeat);
		}

		for (int i = 1; i <= noOfPremiumSeats; i++) {
			String seatNo = Integer.toString(counter) + ch;

			ch++;
			fill++;
			if (fill == noOfSeatsInRow) {
				fill = 0;
				counter++;
				ch = 'A';
			}

			TheaterSeat theaterSeat = new TheaterSeat();
			theaterSeat.setSeatNo(seatNo);
			theaterSeat.setSeatType(SeatType.PREMIUM);
			theaterSeat.setTheater(theater);
			seatList.add(theaterSeat);
		}

		theaterRepository.save(theater);

		return "Theater Seats have been added successfully";
	}
}
