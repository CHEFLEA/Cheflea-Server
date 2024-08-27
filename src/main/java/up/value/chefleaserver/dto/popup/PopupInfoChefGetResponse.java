package up.value.chefleaserver.dto.popup;

import java.time.LocalTime;
import java.util.List;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupImage;

public record PopupInfoChefGetResponse(
        String popupName,
        LocalTime popupStartTime,
        LocalTime popupEndTime,
        List<String> popupImage
) {

    public static PopupInfoChefGetResponse of(Popup popup) {
        return new PopupInfoChefGetResponse(
                popup.getName(),
                popup.getStartTime(),
                popup.getEndTime(),
                popup.getPopupImages()
                        .stream()
                        .map(PopupImage::getImageUrl)
                        .toList()
        );
    }
}
