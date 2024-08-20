package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupCategory;

public record PopupGetResponse(
        Long popupId,
        String popupImage,
        List<String> popupCategories,
        String popupName,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        LocalDate popupPeriod,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime popupEndTime,
        String popupHeadChef,
        Boolean isLiked
) {
    public static PopupGetResponse of(Popup popup, Boolean isLiked) {
        return new PopupGetResponse(
                popup.getId(),
                popup.getImage(),
                popup.getPopupCategories()
                        .stream()
                        .map(PopupCategory::getCategory)
                        .map(Category::getKoreanLabel)
                        .toList(),
                popup.getName(),
                popup.getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                popup.getPeriod(),
                popup.getStartTime(),
                popup.getEndTime(),
                popup.getUserRestaurant()
                        .getUser()
                        .getName(),
                isLiked
        );
    }
}
