package up.value.chefleaserver.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.PopupCategory;
import up.value.chefleaserver.domain.UserPopup;

public record PopupInfosResponse(
        String popupImage,
        List<String> popupCategories,
        String popupName,
        String popupAddress,
        LocalDate popupPeriod,
        LocalTime popupStartTime,
        LocalTime popupEndTime,
        String popupHeadChef
) {
    public static PopupInfosResponse of(UserPopup userPopup) {
        return new PopupInfosResponse(
                userPopup.getPopup()
                        .getImage(),
                userPopup.getPopup()
                        .getPopupCategories()
                        .stream()
                        .map(PopupCategory::getCategory)
                        .map(Category::getKoreanLabel)
                        .toList(),
                userPopup.getPopup()
                        .getName(),
                userPopup.getPopup()
                        .getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                userPopup.getPopup()
                        .getPeriod(),
                userPopup.getPopup()
                        .getStartTime(),
                userPopup.getPopup()
                        .getEndTime(),
                userPopup.getPopup()
                        .getUserRestaurant()
                        .getUser()
                        .getName()
        );
    }
}
