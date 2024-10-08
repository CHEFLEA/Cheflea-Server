package up.value.chefleaserver.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.restaurant.ChefInfoPreviewGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantInfoGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantInfoPreviewGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantReservationPreviewGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantsGetResponse;
import up.value.chefleaserver.repository.RestaurantRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Transactional(readOnly = true)
    public RestaurantsGetResponse getRestaurants(User loginUser) {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        List<RestaurantGetResponse> restaurantGetResponses = restaurants.stream()
                .map(restaurant -> {
                    boolean isLiked = restaurant.getRestaurantLikes()
                            .stream()
                            .anyMatch(restaurantLike -> restaurantLike.getUser().equals(loginUser));
                    return RestaurantGetResponse.of(restaurant, isLiked);
                })
                .toList();
        return RestaurantsGetResponse.of(restaurantGetResponses);
    }

    @Transactional(readOnly = true)
    public RestaurantInfoGetResponse getRestaurant(User loginUser, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RuntimeException::new);
        boolean isLiked = restaurant.getRestaurantLikes()
                .stream()
                .anyMatch(restaurantLike -> restaurantLike.getUser().equals(loginUser));

        return RestaurantInfoGetResponse.of(restaurant, isLiked);
    }

    @Transactional(readOnly = true)
    public RestaurantReservationPreviewGetResponse getRestaurantReservationPreview(User loginUser, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(RuntimeException::new);

        return RestaurantReservationPreviewGetResponse.of(
                RestaurantInfoPreviewGetResponse.of(restaurant),
                ChefInfoPreviewGetResponse.of(loginUser)
        );
    }

    @Transactional(readOnly = true)
    public Restaurant findByRestaurantOrThrow(Long restaurant_id) {
        return restaurantRepository.findById(restaurant_id).orElseThrow(RuntimeException::new);
    }
}
