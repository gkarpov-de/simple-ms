package gk.javabrainsmicroservices.movieratingservice;

import gk.javabrainsmicroservices.movieratingservice.domain.MovieRating;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class MovieRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRatingServiceApplication.class, args);
    }

    @Bean
    List<MovieRating> getMovieRatings() {
        return List.of(
                new MovieRating("user1", "movie1", 3),
                new MovieRating("user1", "movie2", 2),
                new MovieRating("user2", "movie1", 4),
                new MovieRating("user2", "movie2", 5),
                new MovieRating("user3", "movie1", 3),
                new MovieRating("user3", "movie2", 4)
        );
    }

}
