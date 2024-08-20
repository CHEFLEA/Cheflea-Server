package up.value.chefleaserver.dto;

public record UserRestaurantGetResponse(
        Long restaurantId,
        String restaurantImage,
        String restaurantName,
        String restaurantAddress,
        Integer restaurantPrice,
        String auditStatus
) {
}
