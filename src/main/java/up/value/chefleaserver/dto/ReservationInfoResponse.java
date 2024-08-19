package up.value.chefleaserver.dto;

import java.time.LocalDateTime;
import up.value.chefleaserver.domain.UserPopup;

public record ReservationInfoResponse(
        String reservationName,
        String reservationPhoneNumber,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        Long reservationTimeId,
        Integer reservationParty
) {
    public static ReservationInfoResponse of(UserPopup userPopup) {
        return new ReservationInfoResponse(
                userPopup.getName(),
                userPopup.getPhoneNumber(),
                userPopup.getTimeTable()
                        .getStartTime(),
                userPopup.getTimeTable()
                        .getEndTime(),
                userPopup.getId(),
                userPopup.getParty()
        );
    }
}
