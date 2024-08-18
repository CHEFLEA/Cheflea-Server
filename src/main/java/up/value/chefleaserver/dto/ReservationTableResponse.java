package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import up.value.chefleaserver.domain.Popup;

public record ReservationTableResponse(
        Long reservationTimeId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalDateTime reservationStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalDateTime reservationEndTime
) {
    public static List<ReservationTableResponse> of(Popup popup) {
        return popup.getPopupTimeTables()
                .stream()
                .map(timeTable -> new ReservationTableResponse(
                        timeTable.getId(),
                        timeTable.getStartTime(),
                        timeTable.getEndTime()
                ))
                .toList();
    }
}
