package up.value.chefleaserver.dto.popup;

import up.value.chefleaserver.dto.restaurant.ChefRestaurantDTO;

public record ChefReservationDTO(
        Long reservationId,
        ChefRestaurantDTO restaurant,
        ChefPopupDTO popupInfo
) {
    public static ChefReservationDTO of(Long reservationId, ChefRestaurantDTO restaurantDTO, ChefPopupDTO popupDTO) {
        return new ChefReservationDTO(reservationId, restaurantDTO, popupDTO);
    }
}
