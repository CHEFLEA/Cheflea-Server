package up.value.chefleaserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPopup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_popup_id")
    private Long id;

    private String name;

    private String phoneNumber;

    private Integer party;

    @Column
    private String status = "예약 완료";

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "popup_id", nullable = false)
    private Popup popup;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "time_table_id", nullable = false)
    private TimeTable timeTable;
}
