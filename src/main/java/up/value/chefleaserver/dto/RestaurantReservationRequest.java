package up.value.chefleaserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RestaurantReservationRequest(
        Location location,
        String name,
        List<String> restaurantDescriptions,
        ToolDetail tools,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd")
        LocalDate desiredPeriod,
        String restaurantImage,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime desiredStartTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        LocalTime desiredEndTime,
        Integer desiredPrice
) {
}
