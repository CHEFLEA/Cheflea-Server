package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupCategory;
import up.value.chefleaserver.domain.PopupImage;

public record PopupDetailGetResponse(
        List<String> popupImages,
        String popupName,
        List<String> popupCategories,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YY.MM.dd")
        LocalDate popupPeriod,
        HeadChefInfoResponse headChefInfo,
        List<MenuResponse> menus,
        Boolean isLiked
) {
    public static PopupDetailGetResponse of(Popup popup, Boolean isLiked) {
        return new PopupDetailGetResponse(
                popup.getPopupImages()
                        .stream()
                        .map(PopupImage::getImageUrl)
                        .toList(),
                popup.getName(),
                popup.getPopupCategories().stream()
                        .map(PopupCategory::getCategory)
                        .map(Category::getKoreanLabel)
                        .toList(),
                popup.getUserRestaurant()
                        .getRestaurant()
                        .getAddress(),
                popup.getPeriod(),
                HeadChefInfoResponse
                        .of(popup),
                popup.getPopupmenus()
                        .stream()
                        .map(MenuResponse::of)
                        .toList(),
                isLiked
        );
    }
}
