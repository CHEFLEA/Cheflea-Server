package up.value.chefleaserver.dto.restaurant;

import up.value.chefleaserver.domain.Restaurant;

public record ToolGetResponse(
        String hall,
        String kitchen,
        String etc
) {

    public static ToolGetResponse of(Restaurant restaurant) {
        return new ToolGetResponse(
                restaurant.getHall(),
                restaurant.getKitchen(),
                restaurant.getEtc()
        );
    }
}
