package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.UserPopup;

public record ReservationInfoResponse(
        Long reservationId,
        PopupInfosResponse popupInfos,
        String reservationStatus
) {
    public static ReservationInfoResponse of(UserPopup userPopup) {
        return new ReservationInfoResponse(
                userPopup.getId(),
                PopupInfosResponse.of(userPopup),
                userPopup.getStatus()
        );
    }
}
