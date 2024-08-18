package up.value.chefleaserver.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import up.value.chefleaserver.common.Category;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCategory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category cateory;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
