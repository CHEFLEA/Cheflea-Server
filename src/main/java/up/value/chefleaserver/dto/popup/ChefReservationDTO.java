package up.value.chefleaserver.dto.popup;

import up.value.chefleaserver.dto.restaurant.ChefRestaurantDTO;

public record ChefReservationDTO(
        ChefRestaurantDTO restaurant,
        ChefPopupDTO popupInfo
) {
    public static ChefReservationDTO of(ChefRestaurantDTO restaurantDTO, ChefPopupDTO popupDTO) {
        return new ChefReservationDTO(restaurantDTO, popupDTO);
    }
}
