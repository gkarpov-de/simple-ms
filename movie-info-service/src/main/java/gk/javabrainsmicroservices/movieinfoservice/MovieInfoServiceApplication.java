package gk.javabrainsmicroservices.movieinfoservice;

import gk.javabrainsmicroservices.movieinfoservice.domain.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

    @Bean
    List<Movie> getMovies(){
        return List.of(
                new Movie("movie1", "Movie 1", "Movie about somethig 1"),
                new Movie("movie2", "Movie 2", "Movie about somethig 2"),
                new Movie("movie3", "Movie 3", "Movie about somethig 3")
        );
    }
    public static void main(String[] args) {
        SpringApplication.run(MovieInfoServiceApplication.class, args);
    }

}
