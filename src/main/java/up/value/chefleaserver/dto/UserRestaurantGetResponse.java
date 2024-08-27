package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.RestaurantImage;
import up.value.chefleaserver.domain.UserRestaurant;

public record UserRestaurantGetResponse(
        Long restaurantId,
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        Integer restaurantPrice,
        String auditStatus
) {
    public static UserRestaurantGetResponse of(UserRestaurant userRestaurant) {
        String restaurantImage = userRestaurant.getRestaurant()
                .getRestaurantImages()
                .stream()
                .map(RestaurantImage::getImageUrl)
                .toList()
                .get(0);
        return new UserRestaurantGetResponse(
                userRestaurant.getRestaurant()
                        .getId(),
                restaurantImage,
                userRestaurant.getRestaurant()
                        .getName(),
                userRestaurant.getRestaurant()
                        .getAddress(),
                userRestaurant.getRestaurant()
                        .getPrice(),
                userRestaurant.getStatus()
        );
    }
}
