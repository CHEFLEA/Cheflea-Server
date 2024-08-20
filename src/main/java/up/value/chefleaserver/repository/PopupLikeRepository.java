package up.value.chefleaserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.PopupLike;
import up.value.chefleaserver.domain.User;

public interface PopupLikeRepository extends JpaRepository<PopupLike, Long> {
    List<PopupLike> findAllByUser(User user);
}