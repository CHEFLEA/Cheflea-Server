package up.value.chefleaserver.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupLike;
import up.value.chefleaserver.domain.User;

public interface PopupLikeRepository extends JpaRepository<PopupLike, Long> {
    List<PopupLike> findAllByUser(User user);

    Optional<PopupLike> findByPopupAndUser(Popup popup, User user);
}
