package up.value.chefleaserver.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import up.value.chefleaserver.common.Role;
import up.value.chefleaserver.dto.user.RegisterUserRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String chefImage;

    @OneToMany(mappedBy = "user")
    private List<Career> chefCareers;

    @OneToMany(mappedBy = "user")
    private List<UserCategory> categories;

    public void registerChef(RegisterUserRequest registerUserRequest) {
        this.chefImage = registerUserRequest.userImage();
        this.name = registerUserRequest.name();
        this.phoneNumber = registerUserRequest.phoneNumber();
    }
}
