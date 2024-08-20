package up.value.chefleaserver.dto.userRestaurant;

import java.util.List;
import up.value.chefleaserver.dto.menu.UserRestaurantReservationMenuGetResponse;
import up.value.chefleaserver.dto.popup.UserRestaurantReservationPopupGetResponse;
import up.value.chefleaserver.dto.restaurant.UserRestaurantReservationRestaurantGetResponse;

public record UserRestaurantReservationResponse(
        UserRestaurantReservationRestaurantGetResponse restaurant,
        UserRestaurantReservationPopupGetResponse popupInfo,
        List<UserRestaurantReservationMenuGetResponse> menus,
        List<String> foodCategories
) {

    public static UserRestaurantReservationResponse of(
            UserRestaurantReservationRestaurantGetResponse userRestaurantReservationRestaurantGetResponse,
            UserRestaurantReservationPopupGetResponse userRestaurantReservationPopupGetResponse,
            List<UserRestaurantReservationMenuGetResponse> userRestaurantReservationMenusGetResponses,
            List<String> categories) {
        return new UserRestaurantReservationResponse(
                userRestaurantReservationRestaurantGetResponse,
                userRestaurantReservationPopupGetResponse,
                userRestaurantReservationMenusGetResponses,
                categories
        );
    }
}
