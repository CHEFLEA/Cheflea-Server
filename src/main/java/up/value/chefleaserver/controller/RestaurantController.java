package up.value.chefleaserver.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationRequest;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationResponse;
import up.value.chefleaserver.service.UserRestaurantService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final UserRestaurantService userRestaurantService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> reserveRestaurant(Principal principal,
                                                  @RequestBody RestaurantReservationRequest request) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        userRestaurantService.reserveRestaurantOrThrow(loginUser, request);
        return ResponseEntity
                .status(CREATED)
                .build();
    }

    @PostMapping("/{restaurantId}/reservations")
    public ResponseEntity<UserRestaurantReservationResponse> registerUserRestaurantReservation(Principal principal,
                                                                                               @PathVariable("restaurantId") Long restaurantId,
                                                                                               @RequestBody UserRestaurantReservationRequest userRestaurantReservationRequest) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));

        return ResponseEntity
                .status(CREATED)
                .body(userRestaurantService
                        .registerUserRestaurantReservation(restaurantId, loginUser, userRestaurantReservationRequest));
    }
}
