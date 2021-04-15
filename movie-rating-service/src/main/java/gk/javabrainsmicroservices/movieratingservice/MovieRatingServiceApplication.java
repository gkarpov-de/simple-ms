package gk.javabrainsmicroservices.movieratingservice;

import gk.javabrainsmicroservices.movieratingservice.domain.MovieRating;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MovieRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRatingServiceApplication.class, args);
    }

    @Bean
    List<MovieRating> getMovieRatings() {
        return List.of(
                new MovieRating("user1", "100", 3),
                new MovieRating("user1", "200", 2),
                new MovieRating("user2", "200", 4),
                new MovieRating("user2", "550", 5),
                new MovieRating("user3", "550", 3),
                new MovieRating("user3", "100", 4)
        );
    }

}
