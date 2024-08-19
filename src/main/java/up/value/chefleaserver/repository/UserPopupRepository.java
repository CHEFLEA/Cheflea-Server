package up.value.chefleaserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserPopup;

public interface UserPopupRepository extends JpaRepository<UserPopup, Long> {
    List<UserPopup> findAllByUser(User user);
}
