package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantImage;

public record UserRestaurantReservationRestaurantGetResponse(
        String restaurantName,
        List<String> restaurantImages,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer restaurantPrice
) {

    public static UserRestaurantReservationRestaurantGetResponse of(Restaurant restaurant) {
        List<String> restaurantImages = restaurant.getRestaurantImages()
                .stream()
                .map(RestaurantImage::getImageUrl)
                .toList();
        return new UserRestaurantReservationRestaurantGetResponse(
                restaurant.getName(),
                restaurantImages,
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice()
        );
    }
}
