package up.value.chefleaserver.dto.restaurant;

import up.value.chefleaserver.domain.Restaurant;

public record RestaurantGetResponse(
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        Integer restaurantPrice
) {

    public static RestaurantGetResponse of(Restaurant restaurant) {
        return new RestaurantGetResponse(
                restaurant.getImage(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPrice()
        );
    }
}
