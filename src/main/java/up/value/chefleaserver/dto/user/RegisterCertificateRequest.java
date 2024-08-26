package up.value.chefleaserver.dto.user;

public record RegisterCertificateRequest(
        String certificateImage,
        String certificateNumber,
        String certificateType
) {
}
