package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;

public record UserRestaurantReservationRestaurantGetResponse(
        String restaurantName,
        String restaurantImage,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer restaurantPrice
) {

    public static UserRestaurantReservationRestaurantGetResponse of(Restaurant restaurant) {
        return new UserRestaurantReservationRestaurantGetResponse(
                restaurant.getName(),
                restaurant.getImage(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
