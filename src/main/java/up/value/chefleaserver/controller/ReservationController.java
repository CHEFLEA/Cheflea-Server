package up.value.chefleaserver.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.ReservationGetResponse;
import up.value.chefleaserver.dto.ReservationsGetResponse;
import up.value.chefleaserver.dto.popup.ChefReservationGetResponse;
import up.value.chefleaserver.dto.popup.ChefReservationsGetResponse;
import up.value.chefleaserver.service.UserPopupService;
import up.value.chefleaserver.service.UserRestaurantService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final UserService userService;
    private final UserPopupService userPopupService;
    private final UserRestaurantService userRestaurantService;

    @GetMapping("/customer")
    public ResponseEntity<ReservationsGetResponse> getAllReservation(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userPopupService.getAllReservation(loginUser));
    }

    @GetMapping("/client/{reservationId}")
    public ResponseEntity<ReservationGetResponse> getReservationInfo(Principal principal,
                                                                     @PathVariable Long reservationId) {

        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userPopupService.getReservationInfo(reservationId));
    }

    @GetMapping("/chef/{reservationId}")
    public ResponseEntity<ChefReservationGetResponse> getChefReservation(Principal principal,
                                                                         @PathVariable Long reservationId) {
        return ResponseEntity
                .status(OK)
                .body(userPopupService.getChefReservation(reservationId));
    }

    @DeleteMapping("/chef/{reservationId}")
    public ResponseEntity<Void> deleteReservation(Principal principal,
                                                  @PathVariable Long reservationId) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        userRestaurantService.deleteReservation(loginUser, reservationId);
        return ResponseEntity
                .status(NO_CONTENT)
                .build();
    }

    @GetMapping("/chef")
    public ResponseEntity<ChefReservationsGetResponse> getChefReservations(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userRestaurantService.getChefReservations(loginUser));
    }
}
