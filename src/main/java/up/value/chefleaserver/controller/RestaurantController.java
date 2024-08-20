package up.value.chefleaserver.controller;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.service.RestaurantService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Void> reserveRestaurant(Principal principal,
                                                  @RequestBody RestaurantReservationRequest request) {

    }

}
