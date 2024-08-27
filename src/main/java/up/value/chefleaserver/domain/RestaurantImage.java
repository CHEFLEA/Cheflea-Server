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
public class RestaurantImage {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "restaurant_image_id")
    private Long id;

    String imageUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    Restaurant restaurant;

    private RestaurantImage(String imageUrl, Restaurant restaurant) {
        this.imageUrl = imageUrl;
        this.restaurant = restaurant;
    }

    public static RestaurantImage create(String imageUrl, Restaurant restaurant) {
        return new RestaurantImage(imageUrl, restaurant);
    }
}
