package up.value.chefleaserver.dto;

import java.util.List;

public record PopupsGetResponse(
    List<PopupGetResponse> popups
) {
    public static PopupsGetResponse of(List<PopupGetResponse> popupsGetResponse) {
        return new PopupsGetResponse(popupsGetResponse);
    }
}
