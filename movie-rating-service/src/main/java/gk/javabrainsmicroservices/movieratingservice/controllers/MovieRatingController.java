package gk.javabrainsmicroservices.movieratingservice.controllers;

import gk.javabrainsmicroservices.movieratingservice.domain.MovieRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class MovieRatingController {
    @Autowired
    private List<MovieRating> movieRatings;

    @GetMapping("/{movieId}")
    Mono<MovieRating> getRating(@PathVariable String movieId) {
        return Flux.fromIterable(movieRatings)
                .filter(movieRating -> movieRating.getMovieId().equals(movieId))
                .single()
                .onErrorResume(e -> Mono.empty());
    }

    @GetMapping("/user/{userId}")
    Flux<MovieRating> getUserRatings(@PathVariable String userId) {
        return Flux.fromIterable(movieRatings)
                .filter(movieRating -> movieRating.getUserId().equals(userId));
    }
}
