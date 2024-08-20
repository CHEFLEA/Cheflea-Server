package up.value.chefleaserver.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    private String image;

    private String city;

    private String district;

    private String address;

    private String detailedAddress;

    private LocalDate period;

    private LocalTime startTime;

    private LocalTime endTime;

    private String hall;

    private String kitchen;

    private String etc;

    private String status = "심사 완료";

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Restaurant(String name, String image, String city, String district, String address, String detailedAddress,
                      LocalDate period, LocalTime startTime, LocalTime endTime, String hall, String kitchen,
                      String etc, User user) {
        this.name = name;
        this.image = image;
        this.city = city;
        this.district = district;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.period = period;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hall = hall;
        this.kitchen = kitchen;
        this.etc = etc;
        this.user = user;
    }
}
