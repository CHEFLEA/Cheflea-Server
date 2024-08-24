package up.value.chefleaserver.dto.popup;

import java.util.List;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.PopupCategory;
import up.value.chefleaserver.domain.UserPopup;
import up.value.chefleaserver.dto.menu.UserRestaurantReservationMenuGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantChefGetResponse;

public record ChefReservationGetResponse(
        RestaurantChefGetResponse restaurant,
        PopupInfoChefGetResponse popupInfo,
        List<UserRestaurantReservationMenuGetResponse> menus,
        List<String> foodCategories
) {
    public static ChefReservationGetResponse of(UserPopup userPopup) {
        List<UserRestaurantReservationMenuGetResponse> userRestaurantReservationMenuGetResponses = userPopup.getPopup()
                .getPopupmenus()
                .stream()
                .map(UserRestaurantReservationMenuGetResponse::of)
                .toList();

        List<String> categoriesByKoreanLabel = userPopup.getPopup()
                .getPopupCategories()
                .stream()
                .map(PopupCategory::getCategory)
                .map(Category::getKoreanLabel)
                .toList();

        return new ChefReservationGetResponse(
                RestaurantChefGetResponse.of(userPopup.getPopup().getUserRestaurant().getRestaurant()),
                PopupInfoChefGetResponse.of(userPopup.getPopup()),
                userRestaurantReservationMenuGetResponses,
                categoriesByKoreanLabel
        );
    }
}
