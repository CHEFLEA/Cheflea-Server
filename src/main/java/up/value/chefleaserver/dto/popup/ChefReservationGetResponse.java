package up.value.chefleaserver.dto.popup;

import java.util.List;
import up.value.chefleaserver.dto.menu.UserRestaurantReservationMenuGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantChefGetResponse;

public record ChefReservationGetResponse(
        RestaurantChefGetResponse restaurant,
        PopupInfoChefGetResponse popupInfo,
        List<UserRestaurantReservationMenuGetResponse> menus
) {

    public static ChefReservationGetResponse of(RestaurantChefGetResponse restaurantChefGetResponse,
                                                PopupInfoChefGetResponse popupInfoChefGetResponse,
                                                List<UserRestaurantReservationMenuGetResponse> userRestaurantReservationMenuGetResponses) {
        return new ChefReservationGetResponse(
                restaurantChefGetResponse,
                popupInfoChefGetResponse,
                userRestaurantReservationMenuGetResponses
        );
    }
}

