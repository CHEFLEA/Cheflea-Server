package up.value.chefleaserver.dto;

import java.util.List;

public record ReservationInfosResponse(
        List<ReservationInfoResponse> reservationInfos
) {
}
