package up.value.chefleaserver.dto.userRestaurant;

import up.value.chefleaserver.dto.popup.UserRestaurantReservationPopupGetResponse;
import up.value.chefleaserver.dto.restaurant.UserRestaurantReservationRestaurantGetResponse;

public record UserRestaurantReservationResponse(
        UserRestaurantReservationRestaurantGetResponse restaurant,
        UserRestaurantReservationPopupGetResponse popupInfo
) {

    public static UserRestaurantReservationResponse of(
            UserRestaurantReservationRestaurantGetResponse userRestaurantReservationRestaurantGetResponse,
            UserRestaurantReservationPopupGetResponse userRestaurantReservationPopupGetResponse) {
        return new UserRestaurantReservationResponse(
                userRestaurantReservationRestaurantGetResponse,
                userRestaurantReservationPopupGetResponse
        );
    }
}
