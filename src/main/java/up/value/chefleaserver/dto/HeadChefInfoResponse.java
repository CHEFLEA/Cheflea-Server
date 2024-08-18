package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.Career;
import up.value.chefleaserver.domain.Popup;

import java.util.List;

public record HeadChefInfoResponse(
        String headChefName,
        List<String> careers
) {
    public static HeadChefInfoResponse of(Popup popup){
        return new HeadChefInfoResponse(
                popup.getUserRestaurant().getUser().getName(),
                popup.getUserRestaurant().getUser().getChefCareers().stream().map(Career::getDescription).toList()
        );
    }
}
