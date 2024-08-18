package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.Popup;

import java.time.LocalDate;
import java.util.List;

public record PopupDetailGetResponse(
        String popupImage,
        String popupName,
        List<String> popupCategories,
        String popupAddress,
        LocalDate popupPeriod,
        HeadChefInfoResponse headChefInfo,
        List<MenuResponse> menus,
        Boolean isLiked
) {
    public static PopupDetailGetResponse of(Popup popup, HeadChefInfoResponse headChefInfo, Boolean isLiked){
        return new PopupDetailGetResponse(
                popup.getImage(),
                popup.getName(),
                popup.getPopupCategories().stream()
                        .map(popupCategory -> popupCategory.getCategory().getKoreanLabel())
                        .toList(),
                popup.getUserRestaurant().getRestaurant().getAddress(),
                popup.getPeriod(),
                headChefInfo,
                popup.getPopupmenus().stream()
                        .map(MenuResponse::of).toList(),
                isLiked
        );
    }
}
