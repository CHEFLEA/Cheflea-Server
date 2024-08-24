package up.value.chefleaserver.dto.popup;

import java.util.List;
import up.value.chefleaserver.dto.menu.UserRestaurantReservationMenuGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantChefGetResponse;

public record ChefReservationGetResponse(
        RestaurantChefGetResponse restaurant,
        PopupInfoChefGetResponse popupInfo,
        List<UserRestaurantReservationMenuGetResponse> menus,
        List<String> foodCategories
) {

    public static ChefReservationGetResponse of(RestaurantChefGetResponse restaurantChefGetResponse,
                                                PopupInfoChefGetResponse popupInfoChefGetResponse,
                                                List<UserRestaurantReservationMenuGetResponse> userRestaurantReservationMenuGetResponses,
                                                List<String> categories) {
        return new ChefReservationGetResponse(
                restaurantChefGetResponse,
                popupInfoChefGetResponse,
                userRestaurantReservationMenuGetResponses,
                categories
        );
    }
}

