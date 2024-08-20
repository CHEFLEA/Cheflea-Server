package up.value.chefleaserver.dto.menu;

public record MenuRegisterPostRequest(
        String menuImage,
        String menuName,
        Integer menuPrice,
        String menuDescription
) {
}
