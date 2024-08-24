package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantDescription;

public record RestaurantInfoGetResponse(
        Long restaurantId,
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer price,
        List<String> restaurantDescription

) {

    public static RestaurantInfoGetResponse of(Restaurant restaurant) {
        List<String> restaurantDescription = restaurant.getRestaurantDescriptions()
                .stream()
                .map(RestaurantDescription::getDescription)
                .toList();
        return new RestaurantInfoGetResponse(
                restaurant.getId(),
                restaurant.getImage(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice(),
                restaurantDescription
        );
    }
}
