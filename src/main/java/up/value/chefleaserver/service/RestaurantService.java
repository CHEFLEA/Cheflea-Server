package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.repository.RestaurantRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
