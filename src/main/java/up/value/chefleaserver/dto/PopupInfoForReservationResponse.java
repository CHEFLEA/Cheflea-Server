package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.Popup;

public record PopupInfoForReservationResponse(
        String popupName,
        String popupImage,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        LocalDate popupPeriod,
        List<ReservationTableResponse> reservationTable
) {
    public static PopupInfoForReservationResponse of(Popup popup) {
        return new PopupInfoForReservationResponse(
                popup.getName(),
                popup.getImage(),
                popup.getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                popup.getPeriod(),
                ReservationTableResponse
                        .of(popup)
        );
    }
}
