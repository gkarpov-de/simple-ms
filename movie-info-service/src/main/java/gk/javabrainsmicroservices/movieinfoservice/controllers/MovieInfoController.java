package gk.javabrainsmicroservices.movieinfoservice.controllers;

import gk.javabrainsmicroservices.movieinfoservice.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    @Autowired
    List<Movie> movies;

    @GetMapping("/{movieId}")
    public Mono<Movie> getMovieInfo(@PathVariable String movieId) {
        return Flux.fromIterable(movies)
                .filter(movie -> movie.getId().equals(movieId))
                .single();
    }
}
