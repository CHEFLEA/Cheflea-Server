package up.value.chefleaserver.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopupLike {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "popup_id", nullable = false)
    private Popup popup;

    private PopupLike(User user, Popup popup) {
        this.user = user;
        this.popup = popup;
    }

    public static PopupLike create(User user, Popup popup) {
        return new PopupLike(user, popup);
    }
}
