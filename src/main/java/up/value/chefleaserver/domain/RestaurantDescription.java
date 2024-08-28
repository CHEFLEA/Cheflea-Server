package up.value.chefleaserver.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantDescription {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "restaurant_description_id")
    private Long id;

    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private RestaurantDescription(String description, Restaurant restaurant) {
        this.description = description;
        this.restaurant = restaurant;
    }

    public static RestaurantDescription create(String description, Restaurant restaurant) {
        return new RestaurantDescription(description, restaurant);
    }
}
