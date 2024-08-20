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
import lombok.Getter;
import lombok.NoArgsConstructor;
import up.value.chefleaserver.dto.menu.MenuRegisterPostRequest;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name;

    private String description;

    private String image;

    private Integer price;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "popup_id", nullable = false)
    private Popup popup;

    private Menu(MenuRegisterPostRequest menuRegisterPostRequest, Popup popup) {
        this.name = menuRegisterPostRequest.menuName();
        this.description = menuRegisterPostRequest.menuDescription();
        this.image = menuRegisterPostRequest.menuImage();
        this.price = menuRegisterPostRequest.menuPrice();
        this.popup = popup;
    }

    public static Menu create(MenuRegisterPostRequest menuRegisterPostRequest, Popup popup) {
        return new Menu(menuRegisterPostRequest, popup);
    }
}
