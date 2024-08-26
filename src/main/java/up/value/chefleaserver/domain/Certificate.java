package up.value.chefleaserver.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import up.value.chefleaserver.dto.user.RegisterCertificateRequest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certificate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "certificate_id")
    private Long id;

    private String image;

    private String number;

    private String type;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Certificate(User user, RegisterCertificateRequest registerCertificateRequest) {
        this.user = user;
        this.image = registerCertificateRequest.certificateImage();
        this.number = registerCertificateRequest.certificateNumber();
        this.type = registerCertificateRequest.certificateType();
    }

    public static Certificate create(User user, RegisterCertificateRequest registerCertificateRequest) {
        return new Certificate(user, registerCertificateRequest);
    }
}
