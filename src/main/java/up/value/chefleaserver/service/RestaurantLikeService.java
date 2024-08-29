package up.value.chefleaserver.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantLike;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.restaurant.RestaurantGetResponse;
import up.value.chefleaserver.dto.restaurant.RestaurantsGetResponse;
import up.value.chefleaserver.repository.RestaurantLikeRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantLikeService {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final RestaurantLikeRepository restaurantLikeRepository;

    @Transactional(readOnly = true)
    public RestaurantsGetResponse getLikedRestaurants(User loginUser) {
        List<RestaurantLike> restaurantLikes = restaurantLikeRepository.findAllByUser(loginUser);
        List<RestaurantGetResponse> restaurantGetResponses = restaurantLikes.stream()
                .map(restaurantLike -> RestaurantGetResponse.of(restaurantLike.getRestaurant(),
                        userService.isLikedByUser(loginUser, restaurantLike.getRestaurant()))).toList();
        return RestaurantsGetResponse.of(restaurantGetResponses);
    }

    public HttpStatus registerOrDeleteRestaurantLike(User loginUser, Long restaurantId) {
        Restaurant restaurant = restaurantService.findByRestaurantOrThrow(restaurantId);
        Optional<RestaurantLike> restaurantLike = restaurantLikeRepository.findByRestaurantAndUser(restaurant,
                loginUser);
        if (restaurantLike.isPresent()) {
            restaurantLikeRepository.delete(restaurantLike.get());
            return HttpStatus.NO_CONTENT;
        } else {
            restaurantLikeRepository.save(RestaurantLike.create(loginUser, restaurant));
            return HttpStatus.CREATED;
        }
    }
}
