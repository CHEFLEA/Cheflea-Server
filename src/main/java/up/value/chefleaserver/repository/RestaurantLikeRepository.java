package up.value.chefleaserver.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantLike;
import up.value.chefleaserver.domain.User;

public interface RestaurantLikeRepository extends JpaRepository<RestaurantLike, Long> {
    List<RestaurantLike> findAllByUser(User user);

    Optional<RestaurantLike> findByRestaurantAndUser(Restaurant restaurant, User user);
}
