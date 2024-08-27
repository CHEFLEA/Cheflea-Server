package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.PopupImage;
import up.value.chefleaserver.domain.UserPopup;

public record PopupInfoResponse(
        List<String> popupImage,
        String popupName,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd")
        LocalDate popupPeriod
) {
    public static PopupInfoResponse of(UserPopup userPopup) {
        return new PopupInfoResponse(
                userPopup.getPopup()
                        .getPopupImages()
                        .stream()
                        .map(PopupImage::getImageUrl)
                        .toList(),
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
