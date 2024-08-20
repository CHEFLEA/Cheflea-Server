package up.value.chefleaserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserRestaurant;

public interface UserRestaurantRepository extends JpaRepository<UserRestaurant, Long> {
    List<UserRestaurant> findAllByUser(User user);
}