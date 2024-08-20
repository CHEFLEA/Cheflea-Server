package up.value.chefleaserver.dto;

import java.util.List;
import up.value.chefleaserver.domain.UserPopup;

public record ReservationsGetResponse(
        List<ReservationGetResponse> reservationInfos
) {
    public static ReservationsGetResponse of(List<UserPopup> reservationInfos) {
        return new ReservationsGetResponse(
                reservationInfos
                        .stream()
                        .map(ReservationGetResponse::of)
                        .toList()
        );
    }
}
