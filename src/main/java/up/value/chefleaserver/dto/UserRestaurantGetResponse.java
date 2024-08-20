package up.value.chefleaserver.dto;

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
        return new UserRestaurantGetResponse(
                userRestaurant.getRestaurant()
                        .getId(),
                userRestaurant.getRestaurant()
                        .getImage(),
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
