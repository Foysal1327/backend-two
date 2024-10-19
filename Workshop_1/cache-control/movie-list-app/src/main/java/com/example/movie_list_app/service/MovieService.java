package com.example.movie_list_app.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.movie_list_app.model.Movie;

public class MovieService {
    // Sample list of movies for testing
    private List<Movie> movies = Arrays.asList(
        new Movie("Inception", Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt"), "Science Fiction", "2010-07-16", 160000000),
        new Movie("The Dark Knight", Arrays.asList("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000),
        new Movie("Interstellar", Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "Science Fiction", "2014-11-07", 165000000)
    );

    public Movie getMovieByTitle(String title) {
        // Using Optional to handle the case when a movie is not found
        Optional<Movie> movie = movies.stream()
                                      .filter(m -> m.getTitle().equalsIgnoreCase(title))
                                      .findFirst();
        return movie.orElse(null);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }
}
