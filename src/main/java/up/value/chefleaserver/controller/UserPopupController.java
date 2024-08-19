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
import up.value.chefleaserver.service.UserPopupService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class UserPopupController {

    private final UserPopupService userPopupService;
    private final UserService userService;

    @GetMapping("/client/{reservation_id}")
    public ResponseEntity<ReservationGetResponse> getReservationInfo(Principal principal,
                                                                     @PathVariable Long reservation_id) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userPopupService.getReservationInfo(reservation_id));
    }
}
