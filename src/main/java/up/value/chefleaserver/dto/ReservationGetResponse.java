package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.UserPopup;

public record ReservationGetResponse(
        PopupInfoResponse popupInfo,
        ReservationInfoResponse reservationInfo
) {
    public static ReservationGetResponse of(UserPopup popup) {
        return new ReservationGetResponse(
                PopupInfoResponse.of(popup),
                ReservationInfoResponse.of(popup)
        );
    }
}
