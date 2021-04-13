package gk.javabrainsmicroservices.moviecatalogservice.controllers;

import gk.javabrainsmicroservices.moviecatalogservice.domain.Movie;
import gk.javabrainsmicroservices.moviecatalogservice.domain.MovieRating;
import gk.javabrainsmicroservices.moviecatalogservice.domain.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    @Autowired
    WebClient webClient;

    @GetMapping("/{userId}")
    Flux<CatalogItem> getCatalog(@PathVariable String userId) {
        Flux<MovieRating> movieRatingFlux = webClient.get()
                .uri("http://localhost:8103/ratings/user/" + userId)
                .retrieve()
                .bodyToFlux(MovieRating.class);

        return movieRatingFlux
                    .flatMap(movieRating -> webClient
                        .get()
                        .uri("http://localhost:8102/movies/" + movieRating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                            .flatMap(movie -> Mono.just(
                                    new CatalogItem(movie.getName(), movie.getDescription(), movieRating.getRating())))
        );
    }
}
