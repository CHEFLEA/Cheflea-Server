package up.value.chefleaserver.dto.restaurant;

import java.util.List;

public record RestaurantsGetResponse(
        List<RestaurantGetResponse> restaurants
) {

    public static RestaurantsGetResponse of(List<RestaurantGetResponse> restaurantGetResponses) {
        return new RestaurantsGetResponse(restaurantGetResponses);
    }
}
