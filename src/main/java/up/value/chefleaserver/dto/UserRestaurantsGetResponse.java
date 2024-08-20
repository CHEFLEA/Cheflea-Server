package up.value.chefleaserver.dto;

import java.util.List;

public record UserRestaurantsGetResponse(
        List<UserRestaurantGetResponse> restaurants
) {
}
