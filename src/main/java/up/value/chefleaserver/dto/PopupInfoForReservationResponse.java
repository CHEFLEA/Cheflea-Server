package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;

public record PopupInfoForReservationResponse(
        String popupName,
        String popupImage,
        String popupAddress,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
        LocalDate popupPeriod,
        List<ReservationTableResponse> reservationTable
) {
}
