package up.value.chefleaserver.dto.restaurant;

import java.time.LocalDate;
import java.util.List;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantDescription;
import up.value.chefleaserver.domain.RestaurantImage;

public record RestaurantInfoGetResponse(
        Long restaurantId,
        List<String> restaurantImages,
        String restaurantName,
        String restaurantAddress,
        LocalDate restaurantPeriod,
        Integer price,
        List<String> restaurantDescription,
        ToolGetResponse tools,
        Boolean isLiked
) {

    public static RestaurantInfoGetResponse of(Restaurant restaurant, Boolean isLiked) {
        List<String> restaurantDescription = restaurant.getRestaurantDescriptions()
                .stream()
                .map(RestaurantDescription::getDescription)
                .toList();
        List<String> restaurantImages = restaurant.getRestaurantImages()
                .stream()
                .map(RestaurantImage::getImageUrl)
                .toList();
        return new RestaurantInfoGetResponse(
                restaurant.getId(),
                restaurantImages,
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPeriod(),
                restaurant.getPrice(),
                restaurantDescription,
                ToolGetResponse.of(restaurant),
                isLiked
        );
    }
}
