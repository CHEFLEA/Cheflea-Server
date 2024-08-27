package up.value.chefleaserver.dto.popup;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.List;
import up.value.chefleaserver.domain.Popup;

public record UserRestaurantReservationPopupGetResponse(
        String popupName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupEndTime,
        List<String> popupImages
) {

    public static UserRestaurantReservationPopupGetResponse of(Popup popup, List<String> popupImages) {
        return new UserRestaurantReservationPopupGetResponse(
                popup.getName(),
                popup.getStartTime(),
                popup.getEndTime(),
                popupImages
        );
    }
}
