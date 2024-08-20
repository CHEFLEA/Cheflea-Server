package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
