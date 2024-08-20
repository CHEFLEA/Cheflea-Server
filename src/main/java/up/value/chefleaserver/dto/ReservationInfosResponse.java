package up.value.chefleaserver.dto;

import java.util.List;
import up.value.chefleaserver.domain.UserPopup;

public record ReservationInfosResponse(
        List<ReservationInfoResponse> reservationInfos
) {
    public static ReservationInfosResponse of(List<UserPopup> reservationInfos) {
        return new ReservationInfosResponse(
                reservationInfos
                        .stream()
                        .map(ReservationInfoResponse::of)
                        .toList()
        );
    }
}
