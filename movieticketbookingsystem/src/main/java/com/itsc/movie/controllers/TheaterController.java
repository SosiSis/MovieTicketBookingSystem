package com.itsc.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsc.movie.entities.Theater;
import com.itsc.movie.repositories.TheaterRepository;
import com.itsc.movie.request.TheaterRequest;
import com.itsc.movie.request.TheaterSeatRequest;
import com.itsc.movie.services.TheaterService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    private TheaterRepository theaterRepository;
    
    @Autowired
    private TheaterService theaterService;

    // Add new theater
    @PostMapping("/addNew")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequest request) {
        try {
            String result = theaterService.addTheater(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get theater by address
    @GetMapping("/getByName/{address}")
    public ResponseEntity<Theater> getTheaterByName(@PathVariable String address) {
        Theater theater = theaterRepository.findByAddress(address);
        if (theater != null) {
            return new ResponseEntity<>(theater, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add seats to a theater
    @PostMapping("/addTheaterSeat")
    public ResponseEntity<String> addTheaterSeat(@RequestBody TheaterSeatRequest request) {
        try {
            String result = theaterService.addTheaterSeat(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    
}
