package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import up.value.chefleaserver.domain.UserPopup;

public record ReservationInfoResponse(
        String reservationName,
        String reservationPhoneNumber,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalDateTime reservationStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
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
