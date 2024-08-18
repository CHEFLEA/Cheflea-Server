package up.value.chefleaserver.dto;

import up.value.chefleaserver.domain.Menu;

public record MenuResponse(
        String menuImage,
        String menuName,
        String menuDescription,
        Integer menuPrice
) {
    public static MenuResponse of(Menu menu) {
        return new MenuResponse(
                menu.getImage(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice()
        );
    }
}
