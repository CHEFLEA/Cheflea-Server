package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.UserCategory;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
