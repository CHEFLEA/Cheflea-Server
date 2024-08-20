package up.value.chefleaserver.dto.userRestaurant;

import up.value.chefleaserver.dto.restaurant.RestaurantGetResponse;

public record UserRestaurantReservationResponse(
        RestaurantGetResponse restaurant
) {

    public static UserRestaurantReservationResponse of(RestaurantGetResponse restaurantGetResponse) {
        return new UserRestaurantReservationResponse(
                restaurantGetResponse
        );
    }
}
