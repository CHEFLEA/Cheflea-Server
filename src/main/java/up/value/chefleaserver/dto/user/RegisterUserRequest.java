package up.value.chefleaserver.dto.user;

import java.util.List;

public record RegisterUserRequest(
        String userImage,
        String name,
        String phoneNumber,
        List<String> careers,
        List<RegisterCertificateRequest> certificates,
        List<String> foodCategories
) {
}
