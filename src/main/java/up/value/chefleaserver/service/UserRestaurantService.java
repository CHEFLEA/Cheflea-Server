package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserRestaurant;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.repository.UserRestaurantRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRestaurantService {

    private final UserRestaurantRepository userRestaurantRepository;
    private final RestaurantService restaurantService;

    public void reserveRestaurantOrThrow(User user, RestaurantReservationRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .image(request.restaurantImage())
                .city(request.location().city())
                .district(request.location().district())
                .address(request.location().address())
                .detailedAddress(request.location().detailedAddress())
                .period(request.desiredPeriod())
                .startTime(request.desiredStartTime())
                .endTime(request.desiredEndTime())
                .hall(request.tools().hall())
                .kitchen(request.tools().hall())
                .etc(request.tools().etc())
                .user(user)
                .build();
        restaurantService.saveRestaurant(restaurant);
        UserRestaurant userRestaurant = UserRestaurant.builder()
                .user(user)
                .restaurant(restaurant)
                .build();
        userRestaurantRepository.save(userRestaurant);
    }
}
