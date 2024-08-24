package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;

public record RestaurantChefGetResponse(
        String restaurantName,
        String restaurantImage,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer restaurantPrice
) {

    public static RestaurantChefGetResponse of(Restaurant restaurant) {
        return new RestaurantChefGetResponse(
                restaurant.getName(),
                restaurant.getImage(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
