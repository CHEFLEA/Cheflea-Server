package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;

public record RestaurantGetResponse(
        String restaurantName,
        String restaurantImage,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer restaurantPrice
) {

    public static RestaurantGetResponse of(Restaurant restaurant) {
        return new RestaurantGetResponse(
                restaurant.getName(),
                restaurant.getImage(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
