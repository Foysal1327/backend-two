package com.example.movie_list_app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_list_app.model.Movie;
import com.example.movie_list_app.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        // Apply Cache-Control for 1 hour
        CacheControl cacheControl = CacheControl.maxAge(1, TimeUnit.HOURS)
                .cachePublic();

        return ResponseEntity.ok()
                .cacheControl(cacheControl) // Apply cache control
                .body(movies);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        Movie movie = movieService.getMovieByTitle(title);

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        // Apply Cache-Control for 30 minutes
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES)
                                                .mustRevalidate();

        return ResponseEntity.ok()
                .cacheControl(cacheControl) 
                .body(movie);
    }
}
