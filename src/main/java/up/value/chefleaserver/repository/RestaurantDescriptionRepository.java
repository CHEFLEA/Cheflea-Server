package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.RestaurantDescription;

public interface RestaurantDescriptionRepository extends JpaRepository<RestaurantDescription, Long> {
}
