package up.value.chefleaserver.dto;

import java.util.List;
import up.value.chefleaserver.domain.UserPopup;

public record ReservationsGetResponse(
        List<UserPopupGetResponse> reservationInfos
) {
    public static ReservationsGetResponse of(List<UserPopup> reservationInfos) {
        return new ReservationsGetResponse(
                reservationInfos
                        .stream()
                        .map(UserPopupGetResponse::of)
                        .toList()
        );
    }
}
