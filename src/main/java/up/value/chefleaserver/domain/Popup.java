package up.value.chefleaserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Popup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_id")
    private Long id;

    private String name;

    private String image;

    private String address;

    private LocalDate period;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
