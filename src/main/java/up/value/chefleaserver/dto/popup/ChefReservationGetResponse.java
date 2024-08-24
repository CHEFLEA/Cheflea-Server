package up.value.chefleaserver.dto.popup;

import up.value.chefleaserver.dto.restaurant.RestaurantChefGetResponse;

public record ChefReservationGetResponse(
        RestaurantChefGetResponse restaurant,
        PopupInfoChefGetResponse popupInfo
) {

    public static ChefReservationGetResponse of(RestaurantChefGetResponse restaurantChefGetResponse,
                                                PopupInfoChefGetResponse popupInfoChefGetResponse) {
        return new ChefReservationGetResponse(
                restaurantChefGetResponse,
                popupInfoChefGetResponse
        );
    }
}

