package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
