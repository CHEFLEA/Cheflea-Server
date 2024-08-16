package up.value.chefleaserver.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Description {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "desciption_id")
    private Long id;

    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "restaurant_id",nullable = false)
    private Restaurant restaurant;
}
