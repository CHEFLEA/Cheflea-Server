package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;

public record ChefRestaurantDTO(
        String restaurantName,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        String restaurantStatus
) {

    public static ChefRestaurantDTO of(Restaurant restaurant) {
        return new ChefRestaurantDTO(
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getStatus()
        );
    }
}
