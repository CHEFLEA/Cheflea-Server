package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import up.value.chefleaserver.domain.UserPopup;

public record PopupInfoResponse(
        String popupImage,
        String popupName,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd")
        LocalDate popupPeriod
) {
    public static PopupInfoResponse of(UserPopup userPopup) {
        return new PopupInfoResponse(
                userPopup.getPopup()
                        .getImage(),
                userPopup.getPopup()
                        .getName(),
                userPopup.getPopup()
                        .getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                userPopup.getPopup()
                        .getPeriod()
        );
    }
}
