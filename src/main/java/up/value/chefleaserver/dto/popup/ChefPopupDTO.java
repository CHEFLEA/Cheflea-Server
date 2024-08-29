package up.value.chefleaserver.dto.popup;

import static java.time.Duration.between;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupImage;

public record ChefPopupDTO(
        String popupName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupEndTime,
        String popupImage,
        Integer restaurantTotalPrice
) {

    public static ChefPopupDTO of(Popup popup) {
        long hours = between(popup.getEndTime(), popup.getStartTime())
                .toHours();

        return new ChefPopupDTO(
                popup.getName(),
                popup.getStartTime(),
                popup.getEndTime(),
                popup.getPopupImages()
                        .stream()
                        .map(PopupImage::getImageUrl)
                        .toList()
                        .get(0),
                (int) (popup.getUserRestaurant().getRestaurant().getPrice() * hours)
        );
    }
}
