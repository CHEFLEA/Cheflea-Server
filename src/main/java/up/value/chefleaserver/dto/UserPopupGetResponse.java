package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.UserPopup;

public record UserPopupGetResponse(
        Long reservationId,
        PopupInfosResponse popupInfos,
        String reservationStatus
) {
    public static UserPopupGetResponse of(UserPopup userPopup) {
        return new UserPopupGetResponse(
                userPopup.getId(),
                PopupInfosResponse.of(userPopup),
                userPopup.getStatus()
        );
    }
}
