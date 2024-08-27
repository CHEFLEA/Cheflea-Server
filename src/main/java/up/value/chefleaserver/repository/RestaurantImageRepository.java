package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.RestaurantImage;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
}
