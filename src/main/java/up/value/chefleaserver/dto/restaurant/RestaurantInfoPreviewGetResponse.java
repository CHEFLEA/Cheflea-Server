package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantImage;

public record RestaurantInfoPreviewGetResponse(
        String restaurantName,
        String restaurantImage,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer restaurantPrice
) {

    public static RestaurantInfoPreviewGetResponse of(Restaurant restaurant) {
        String restaurantImage = restaurant.getRestaurantImages()
                .stream()
                .map(RestaurantImage::getImageUrl)
                .toList()
                .get(0);

        return new RestaurantInfoPreviewGetResponse(
                restaurant.getName(),
                restaurantImage,
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
