package up.value.chefleaserver.dto.restaurant;

import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantImage;

public record RestaurantGetResponse(
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        Integer restaurantPrice,
        Boolean isLiked
) {

    public static RestaurantGetResponse of(Restaurant restaurant, Boolean isLiked) {
        return new RestaurantGetResponse(
                restaurant.getRestaurantImages()
                        .stream()
                        .map(RestaurantImage::getImageUrl)
                        .toList()
                        .get(0),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPrice(),
                isLiked
        );
    }
}
