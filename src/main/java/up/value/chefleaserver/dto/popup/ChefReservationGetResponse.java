package up.value.chefleaserver.dto.popup;

import up.value.chefleaserver.dto.restaurant.RestaurantChefGetResponse;

public record ChefReservationGetResponse(
        RestaurantChefGetResponse restaurant
) {

    public static ChefReservationGetResponse of(RestaurantChefGetResponse restaurantChefGetResponse) {
        return new ChefReservationGetResponse(
                restaurantChefGetResponse
        );
    }
}

