package up.value.chefleaserver.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupLike;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupGetResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.repository.PopupLikeRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PopupLikeService {

    private final PopupLikeRepository popupLikeRepository;
    private final UserService userService;
    private final PopupService popupService;

    @Transactional(readOnly = true)
    public PopupsGetResponse getPopupFavorites(User loginUser) {
        List<PopupLike> popupLikes = popupLikeRepository.findAllByUser(loginUser);
        List<PopupGetResponse> popupGetResponses = popupLikes.stream()
                .map(popupLike -> PopupGetResponse.of(
                        popupLike.getPopup(),
                        userService.isLikedByUser(loginUser, popupLike.getPopup())))
                .toList();
        return PopupsGetResponse.of(popupGetResponses);
    }

    public HttpStatus registerOrDeletePopupLike(User loginUser, Long popupId) {
        Popup popup = popupService.getPopupOrThrow(popupId);
        Optional<PopupLike> popupLike = popupLikeRepository.findByPopupAndUser(popup, loginUser);
        if (popupLike.isPresent()) {
            popupLikeRepository.delete(popupLike.get());
            return HttpStatus.NO_CONTENT;
        } else {
            popupLikeRepository.save(PopupLike.create(loginUser, popup));
            return HttpStatus.CREATED;
        }
    }
}
