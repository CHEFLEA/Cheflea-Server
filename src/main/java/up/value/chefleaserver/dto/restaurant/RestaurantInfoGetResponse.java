package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;

public record RestaurantInfoGetResponse(
        Long restaurantId,
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer price

) {

    public static RestaurantInfoGetResponse of(Restaurant restaurant) {
        return new RestaurantInfoGetResponse(
                restaurant.getId(),
                restaurant.getImage(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
