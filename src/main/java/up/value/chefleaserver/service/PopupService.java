package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupLike;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupGetResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.repository.PopupRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PopupService {

    private final PopupRepository popupRepository;

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
}
