package up.value.chefleaserver.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.UserRestaurant;

public interface PopupRepository extends JpaRepository<Popup, Long> {
    List<Popup> findByNameContainingOrderByCreatedTimeDesc(String searchKeyword);

    List<Popup> findByNameContainingOrderByPopupLikesDesc(String searchKeyword);

    List<Popup> findByNameContainingOrderByPeriodDesc(String searchKeyword);

    List<Popup> findByNameContainingOrderByPeriodDescCreatedTimeDescPopupLikesDesc(String searchKeyword);

    Popup findByUserRestaurant(UserRestaurant userRestaurant);
}
