package up.value.chefleaserver.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record RestaurantReservationRequest(
        Location location,
        List<String> restaurantDescriptions,
        ToolDetail tools,
        LocalDate desiredPeriod,
        String restaurantImage,
        LocalDateTime desiredStartTime,
        LocalDateTime desiredEndTime,
        Integer desiredPrice
) {
}
