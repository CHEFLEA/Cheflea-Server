package up.value.chefleaserver.dto;

import java.util.List;

public record ReservationInfosResponse(
        List<ReservationInfoResponse> reservationInfos
) {
    public static ReservationInfosResponse of(List<ReservationInfoResponse> reservationInfos) {
        return new ReservationInfosResponse(
                reservationInfos
        );
    }
}
