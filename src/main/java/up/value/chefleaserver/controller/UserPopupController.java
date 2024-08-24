package up.value.chefleaserver.controller;

import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.ReservationGetResponse;
import up.value.chefleaserver.dto.ReservationsGetResponse;
import up.value.chefleaserver.dto.popup.ChefReservationGetResponse;
import up.value.chefleaserver.service.UserPopupService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class UserPopupController {

    private final UserService userService;
    private final UserPopupService userPopupService;

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

}
    
