package up.value.chefleaserver.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserRestaurant;
import up.value.chefleaserver.dto.UserRestaurantsGetResponse;
import up.value.chefleaserver.repository.UserRestaurantRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRestaurantService {

    private final UserRestaurantRepository userRestaurantRepository;

    @Transactional(readOnly = true)
    public UserRestaurantsGetResponse getAllRegisteredRestaurant(User user) {
        List<UserRestaurant> userRestaurants = userRestaurantRepository.findAllByUser(user);
        return UserRestaurantsGetResponse.of(userRestaurants);
    }
}