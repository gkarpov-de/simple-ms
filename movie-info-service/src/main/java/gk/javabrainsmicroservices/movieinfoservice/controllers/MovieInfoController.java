package gk.javabrainsmicroservices.movieinfoservice.controllers;

import gk.javabrainsmicroservices.movieinfoservice.domain.Movie;
import gk.javabrainsmicroservices.movieinfoservice.domain.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    @Autowired
    private WebClient.Builder loadBalanced;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/{movieId}")
    public Mono<Movie> getMovieInfo(@PathVariable String movieId) {
        return loadBalanced.build()
                .get()
                .uri("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey)
                .retrieve()
                .bodyToMono(MovieSummary.class)
                .flatMap(movieSummary -> Mono.just(
                        new Movie(movieSummary.getId(),
                                movieSummary.getTitle(),
                                movieSummary.getOverview())
                ));
    }
}
