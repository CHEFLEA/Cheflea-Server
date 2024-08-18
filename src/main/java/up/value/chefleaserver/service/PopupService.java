package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.HeadChefInfoResponse;
import up.value.chefleaserver.dto.PopupDetailGetResponse;
import up.value.chefleaserver.dto.PopupGetResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.repository.PopupRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PopupService {

    private final PopupRepository popupRepository;

    private final UserService userService;

    @Transactional(readOnly = true)
    public PopupsGetResponse getAllPopups(User loginUser) {
        List<Popup> popups = popupRepository.findAll();

        List<PopupGetResponse> popupGetResponses = popups.stream()
                .map(popup -> {
                    boolean isLiked = userService.isLikedByUser(loginUser, popup);
                    return PopupGetResponse.of(popup, isLiked);
                })
                .toList();

        return PopupsGetResponse.of(popupGetResponses);
    }

    public PopupDetailGetResponse getPopup(User loginUser, Long popupId) {
        Popup popup = popupRepository.findById(popupId).orElseThrow(RuntimeException::new);
        return PopupDetailGetResponse.of(popup,HeadChefInfoResponse.of(popup),userService.isLikedByUser(loginUser, popup));
    }
}
