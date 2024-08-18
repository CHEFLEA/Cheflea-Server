package up.value.chefleaserver.controller;


import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupDetailGetResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.dto.ReservationRequest;
import up.value.chefleaserver.service.PopupService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pop-ups")
public class PopupController {

    private final PopupService popupService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<PopupsGetResponse> getAllPopups(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.getAllPopups(loginUser));
    }

    @GetMapping("/{popupId}")
    public ResponseEntity<PopupDetailGetResponse> getPopup(Principal principal, @PathVariable("popupId") Long popupId) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.getPopup(loginUser, popupId));
    }

    @PostMapping("/{popupId}/reservations")
    public ResponseEntity<Void> reservePopup(Principal principal, @PathVariable("popupId") Long popupId, @RequestBody
    ReservationRequest request) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.reservePopup(loginUser, popupId, request));
    }
}
