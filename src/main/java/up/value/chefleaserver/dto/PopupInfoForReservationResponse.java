package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupImage;

public record PopupInfoForReservationResponse(
        String popupName,
        List<String> popupImage,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd")
        LocalDate popupPeriod,
        List<ReservationTableResponse> reservationTable
) {
    public static PopupInfoForReservationResponse of(Popup popup) {
        return new PopupInfoForReservationResponse(
                popup.getName(),
                popup.getPopupImages()
                        .stream()
                        .map(PopupImage::getImageUrl)
                        .toList(),
                popup.getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                popup.getPeriod(),
                ReservationTableResponse
                        .of(popup)
        );
    }
}
