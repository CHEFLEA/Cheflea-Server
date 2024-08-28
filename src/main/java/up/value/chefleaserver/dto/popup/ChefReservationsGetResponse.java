package up.value.chefleaserver.dto.popup;

import java.util.List;

public record ChefReservationsGetResponse(
        List<ChefReservationDTO> reservations
) {

    public static ChefReservationsGetResponse of(List<ChefReservationDTO> reservations) {
        return new ChefReservationsGetResponse(reservations);
    }
}
