package up.value.chefleaserver.dto;

public record ReservationRequest(
        String reservationName,
        String reservationPhoneNumber,
        Long reservationTimeId,
        Integer reservationParty
) {
}
