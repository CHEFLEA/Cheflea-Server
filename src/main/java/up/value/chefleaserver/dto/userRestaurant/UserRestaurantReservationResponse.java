package up.value.chefleaserver.dto.userRestaurant;

import up.value.chefleaserver.dto.restaurant.UserRestaurantReservationRestaurantGetResponse;

public record UserRestaurantReservationResponse(
        UserRestaurantReservationRestaurantGetResponse restaurant
) {

    public static UserRestaurantReservationResponse of(
            UserRestaurantReservationRestaurantGetResponse userRestaurantReservationRestaurantGetResponse) {
        return new UserRestaurantReservationResponse(
                userRestaurantReservationRestaurantGetResponse
        );
    }
}
