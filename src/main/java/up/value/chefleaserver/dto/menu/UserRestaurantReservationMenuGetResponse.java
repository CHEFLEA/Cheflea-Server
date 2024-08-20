package up.value.chefleaserver.dto.menu;

import up.value.chefleaserver.domain.Menu;

public record UserRestaurantReservationMenuGetResponse(
        String menuImage,
        String menuName,
        Integer menuPrice,
        String menuDescription
) {

    public static UserRestaurantReservationMenuGetResponse of(Menu menu) {
        return new UserRestaurantReservationMenuGetResponse(
                menu.getImage(),
                menu.getName(),
                menu.getPrice(),
                menu.getDescription()
        );
    }
}
