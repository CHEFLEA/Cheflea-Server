package up.value.chefleaserver.controller;

import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.service.UserPopupService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class UserPopupController {

    private final UserService userService;
    private final UserPopupService userPopupService;

    @GetMapping("/customer")
    public ResponseEntity<ReservationInfosResponse> getAllReservation(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userPopupService.getAllReservation(loginUser));
    }
}
