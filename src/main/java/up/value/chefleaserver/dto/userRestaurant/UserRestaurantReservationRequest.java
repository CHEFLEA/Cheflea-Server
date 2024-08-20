package up.value.chefleaserver.dto.userRestaurant;

import java.util.List;
import up.value.chefleaserver.dto.menu.MenuRegisterPostRequest;
import up.value.chefleaserver.dto.popup.PopupRegisterPostRequest;

public record UserRestaurantReservationRequest(
        PopupRegisterPostRequest popupInfo,
        List<MenuRegisterPostRequest> menus
) {
}
