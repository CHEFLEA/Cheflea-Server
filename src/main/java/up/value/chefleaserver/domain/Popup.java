package up.value.chefleaserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Popup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_id")
    private Long id;

    private String name;

    private String image;

    private LocalDate period;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "popup")
    private List<PopupCategory> popupCategories = new ArrayList<>();

    @OneToMany(mappedBy = "popup")
    private List<PopupLike> popupLikes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_restaurant_id", nullable = false)
    private UserRestaurant userRestaurant;
}
