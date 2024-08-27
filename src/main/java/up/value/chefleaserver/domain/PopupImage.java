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
public class PopupImage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_image_id")
    private Long id;

    @Column(name = "image_url")
    String imageUrl;

    @ManyToOne
    @JoinColumn(name = "popup_id", nullable = false)
    Popup popup;

    private PopupImage(String imageUrl, Popup popup) {
        this.imageUrl = imageUrl;
        this.popup = popup;
    }

    public static PopupImage create(String imageUrl, Popup popup) {
        return new PopupImage(imageUrl, popup);
    }
}
