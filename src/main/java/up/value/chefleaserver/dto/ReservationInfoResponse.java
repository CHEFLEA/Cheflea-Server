package up.value.chefleaserver.dto;

public record ReservationInfoResponse(
        Long reservationId,
        PopupInfosResponse popupInfos,
        String reservationStatus
) {
}
