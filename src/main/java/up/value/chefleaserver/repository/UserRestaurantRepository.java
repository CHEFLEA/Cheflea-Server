package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.UserRestaurant;

public interface UserRestaurantRepository extends JpaRepository<UserRestaurant, Long> {
}
