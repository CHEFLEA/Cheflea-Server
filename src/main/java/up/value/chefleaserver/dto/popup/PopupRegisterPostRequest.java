package up.value.chefleaserver.dto.popup;

import java.time.LocalTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public record PopupRegisterPostRequest(
        String popupName,
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime popupStartTime,
        @DateTimeFormat(pattern = "HH:mm")
        LocalTime popupEndTime,
        List<String> popupImages
) {
}
