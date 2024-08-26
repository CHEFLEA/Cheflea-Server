package up.value.chefleaserver.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupDetailGetResponse;
import up.value.chefleaserver.dto.PopupGetResponse;
import up.value.chefleaserver.dto.PopupInfoForReservationResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.dto.ReservationRequest;
import up.value.chefleaserver.repository.PopupRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PopupService {

    private final PopupRepository popupRepository;
    private final UserService userService;
    private final UserPopupService userPopupService;

    @Transactional(readOnly = true)
    public PopupDetailGetResponse getPopup(User loginUser, Long popupId) {
        Popup popup = popupRepository.findById(popupId).orElseThrow(RuntimeException::new);
        boolean isLiked = userService.isLikedByUser(loginUser, popup);
        return PopupDetailGetResponse.of(popup, isLiked);
    }

    @Transactional(readOnly = true)
    public PopupInfoForReservationResponse getPopupInfoForReservation(User loginUser, Long popupId) {
        Popup popup = popupRepository.findById(popupId).orElseThrow(RuntimeException::new);
        return PopupInfoForReservationResponse.of(popup);
    }

    public void reservePopup(User loginUser, Long popupId, ReservationRequest request) {
        Popup popup = popupRepository.findById(popupId).orElseThrow(RuntimeException::new);
        userPopupService.saveReservationInfo(loginUser, popup, request);
    }

    @Transactional(readOnly = true)
    public PopupsGetResponse getFilteredPopups(User loginUser, Optional<FilterCategory> filterCategory,
                                               Optional<String> searchKeyword) {
        FilterCategory filter = filterCategory.orElse(RECOMMENDATION);
        String keyword = searchKeyword.orElse("");
        List<Popup> popups = new ArrayList<>();
        if ((RECOMMENDATION).equals(filter)) {
            popups = popupRepository.findByNameContainingOrderByPeriodDescCreatedTimeDescPopupLikesDesc(keyword);
        }
        if ((CREATED_TIME_DESC).equals(filter)) {
            popups = popupRepository.findByNameContainingOrderByCreatedTimeDesc(keyword);
        }
        if ((LIKE_DESC).equals(filter)) {
            popups = popupRepository.findByNameContainingOrderByPopupLikesDesc(keyword);
        }
        if ((PERIOD_DESC).equals(filter)) {
            popups = popupRepository.findByNameContainingOrderByPeriodDesc(keyword);
        }
        List<PopupGetResponse> popupGetResponses = popups.stream()
                .map(popup -> {
                    System.out.println("popup = " + popup.toString());
                    boolean isLiked = userService.isLikedByUser(loginUser, popup);
                    return PopupGetResponse.of(popup, isLiked);
                })
                .toList();

        return PopupsGetResponse.of(popupGetResponses);
    }
}
