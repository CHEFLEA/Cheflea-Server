package up.value.chefleaserver.dto.restaurant;

public record RestaurantReservationPreviewGetResponse(
        RestaurantInfoPreviewGetResponse restaurant,
        ChefInfoPreviewGetResponse chef
) {

    public static RestaurantReservationPreviewGetResponse of(
            RestaurantInfoPreviewGetResponse restaurantInfoPreviewGetResponse,
            ChefInfoPreviewGetResponse chefInfoPreviewGetResponse) {
        return new RestaurantReservationPreviewGetResponse(
                restaurantInfoPreviewGetResponse,
                chefInfoPreviewGetResponse
        );
    }
}
