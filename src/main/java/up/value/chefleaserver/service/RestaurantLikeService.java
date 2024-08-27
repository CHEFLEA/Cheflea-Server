package up.value.chefleaserver.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final RestaurantLikeRepository restaurantLikeRepository;

    @Transactional(readOnly = true)
    public RestaurantsGetResponse getLikedRestaurants(User loginUser) {
        List<RestaurantLike> restaurantLikes = restaurantLikeRepository.findAllByUser(loginUser);
        List<RestaurantGetResponse> restaurantGetResponses = restaurantLikes.stream()
                .map(restaurantLike -> RestaurantGetResponse.of(restaurantLike.getRestaurant(),
                        userService.isLikedByUser(loginUser, restaurantLike.getRestaurant()))).toList();
        return RestaurantsGetResponse.of(restaurantGetResponses);
    }
}
