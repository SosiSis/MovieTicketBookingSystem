package com.itsc.movie.request;

import lombok.Data;

import java.sql.Date;

import com.itsc.movie.enums.Genre;
import com.itsc.movie.enums.Language;

@Data
public class MovieRequest {
	private String movieName;
	private Integer duration;
	private Double rating;
	private Date releaseDate;
	private Genre genre;
	private Language language;
}
