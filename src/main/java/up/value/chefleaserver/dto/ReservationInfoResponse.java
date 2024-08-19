package up.value.chefleaserver.dto;

import java.time.LocalDateTime;

public record ReservationInfoResponse(
        String reservationName,
        String reservationPhoneNumber,
        LocalDateTime reservationStartTime,
        LocalDateTime reservationEndTime,
        Long reservationTimeId,
        Integer reservationParty
) {
}
