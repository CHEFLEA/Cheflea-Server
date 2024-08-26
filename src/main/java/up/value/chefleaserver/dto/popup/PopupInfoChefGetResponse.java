package up.value.chefleaserver.dto.popup;

import java.time.LocalTime;
import up.value.chefleaserver.domain.Popup;

public record PopupInfoChefGetResponse(
        String popupName,
        LocalTime popupStartTime,
        LocalTime popupEndTime,
        String popupImage
) {

    public static PopupInfoChefGetResponse of(Popup popup) {
        return new PopupInfoChefGetResponse(
                popup.getName(),
                popup.getStartTime(),
                popup.getEndTime(),
                popup.getImage()
        );
    }
}
