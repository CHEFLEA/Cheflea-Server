package up.value.chefleaserver.config.jwt;

public enum JwtValidationType {
    VALID_TOKEN,
    INVALID_SIGNATURE,
    INVALID_TOKEN,
    EXPIRED_TOKEN,
    UNSUPPORTED_TOKEN,
    EMPTY_TOKEN
}
