package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
