package up.value.chefleaserver.domain;


import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeTable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "timetable_id")
    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "popup_id", nullable = false)
    private Popup popup;

    private TimeTable(LocalDateTime startTime, LocalDateTime endTime, Popup popup) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.popup = popup;
    }

    public static TimeTable create(LocalDateTime startTime, LocalDateTime endTime, Popup popup) {
        return new TimeTable(startTime, endTime, popup);
    }
}
