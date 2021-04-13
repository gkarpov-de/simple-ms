package gk.javabrainsmicroservices.moviecatalogservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRating {
    private String userId;
    private String movieId;
    private Integer rating;
}
