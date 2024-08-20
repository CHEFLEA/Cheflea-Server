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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "time_table_id", nullable = false)
    private TimeTable timeTable;

    @Builder
    public UserPopup(String name, String phoneNumber, Integer party, User user, Popup popup, TimeTable timeTable) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.party = party;
        this.user = user;
        this.popup = popup;
        this.timeTable = timeTable;
    }
}
