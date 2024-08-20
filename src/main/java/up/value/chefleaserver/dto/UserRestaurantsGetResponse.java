package up.value.chefleaserver.dto;

import java.util.List;
import up.value.chefleaserver.domain.UserRestaurant;

public record UserRestaurantsGetResponse(
        List<UserRestaurantGetResponse> restaurants
) {
    public static UserRestaurantsGetResponse of(List<UserRestaurant> userRestaurants) {
        return new UserRestaurantsGetResponse(
                userRestaurants.stream()
                        .map(UserRestaurantGetResponse::of)
                        .toList()
        );
    }
}
