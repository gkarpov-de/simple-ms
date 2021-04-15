package gk.javabrainsmicroservices.moviecatalogservice.controllers;

import gk.javabrainsmicroservices.moviecatalogservice.domain.CatalogItem;
import gk.javabrainsmicroservices.moviecatalogservice.domain.Movie;
import gk.javabrainsmicroservices.moviecatalogservice.domain.MovieRating;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    @LoadBalanced
    private WebClient.Builder loadBalanced;

    @GetMapping("/{userId}")
    Flux<CatalogItem> getCatalog(@PathVariable String userId) {
        return loadBalanced.build()
                .get()
                .uri("http://MOVIE-RATING-SERVICE/ratings/user/" + userId)
                .retrieve()
                .bodyToFlux(MovieRating.class)
                .flatMap(movieRating -> loadBalanced.build()
                        .get()
                        .uri("http://MOVIE-INFO-SERVICE/movies/" + movieRating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .flatMap(movie -> Mono.just(
                                new CatalogItem(movie.getName(), movie.getDescription(), movieRating.getRating())))
                );
    }
}
