package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.UserPopup;

public record ReservationGetResponse(
        Long reservationId,
        PopupInfosResponse popupInfos,
        String reservationStatus
) {
    public static ReservationGetResponse of(UserPopup userPopup) {
        return new ReservationGetResponse(
                userPopup.getId(),
                PopupInfosResponse.of(userPopup),
                userPopup.getStatus()
        );
    }
}
