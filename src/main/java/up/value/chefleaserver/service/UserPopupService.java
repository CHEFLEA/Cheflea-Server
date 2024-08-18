package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserPopup;
import up.value.chefleaserver.dto.ReservationRequest;
import up.value.chefleaserver.repository.UserPopupRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPopupService {

    private final UserPopupRepository userPopupRepository;
    private final TimeTableService timeTableService;

    public void saveReservationInfo(User user, Popup popup, ReservationRequest request) {
        UserPopup userPopup = UserPopup.builder()
                .name(request.reservationName())
                .phoneNumber(request.reservationPhoneNumber())
                .party(request.reservationParty())
                .popup(popup)
                .user(user)
                .timeTable(timeTableService.findById(request.reservationTimeId()))
                .build();

        UserPopup savedUserPopup = userPopupRepository.save(userPopup);
        if (userPopup.equals(savedUserPopup)) {
            return;
        }
        throw new RuntimeException("저장 실패");
    }
}
