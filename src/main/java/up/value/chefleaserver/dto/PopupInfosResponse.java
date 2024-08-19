package up.value.chefleaserver.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record PopupInfosResponse(
        String popupImage,
        List<String> popupCategories,
        String popupName,
        String popupAddress,
        LocalDate popupPeriod,
        LocalDateTime popupStartTime,
        LocalDateTime popupEndTime,
        String popupHeadChef
) {
}
