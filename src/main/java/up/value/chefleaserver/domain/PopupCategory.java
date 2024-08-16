package up.value.chefleaserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import up.value.chefleaserver.common.Category;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopupCategory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "popup_category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;
}
