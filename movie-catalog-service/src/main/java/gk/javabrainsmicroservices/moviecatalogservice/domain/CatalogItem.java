package gk.javabrainsmicroservices.moviecatalogservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {
    private String name;
    private String description;
    private Integer rating;
}
