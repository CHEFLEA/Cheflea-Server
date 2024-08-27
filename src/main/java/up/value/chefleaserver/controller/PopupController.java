package up.value.chefleaserver.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.common.FilterCategory;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupDetailGetResponse;
import up.value.chefleaserver.dto.PopupInfoForReservationResponse;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.dto.ReservationRequest;
import up.value.chefleaserver.service.PopupLikeService;
import up.value.chefleaserver.service.PopupService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pop-ups")
public class PopupController {

    private final PopupService popupService;
    private final UserService userService;
    private final PopupLikeService popupLikeService;

    @GetMapping("/{popupId}")
    public ResponseEntity<PopupDetailGetResponse> getPopup(Principal principal, @PathVariable("popupId") Long popupId) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.getPopup(loginUser, popupId));
    }

    @GetMapping("/{popupId}/reservations")
    public ResponseEntity<PopupInfoForReservationResponse> getPopupInfoForReservation(Principal principal,
                                                                                      @PathVariable("popupId") Long popupId) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.getPopupInfoForReservation(loginUser, popupId));
    }

    @PostMapping("/{popupId}/reservations")
    public ResponseEntity<Void> reservePopup(Principal principal, @PathVariable("popupId") Long popupId,
                                             @RequestBody ReservationRequest request) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        popupService.reservePopup(loginUser, popupId, request);
        return ResponseEntity
                .status(CREATED)
                .build();
    }

    @GetMapping("/likes")
    public ResponseEntity<PopupsGetResponse> getPopupFavorites(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupLikeService.getPopupFavorites(loginUser));
    }

    @PostMapping("/likes/{popup_id}")
    ResponseEntity<Void> registerPopupLike(Principal principal, @PathVariable("popup_id") Long popupId) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(popupLikeService.registerOrDeletePopupLike(loginUser, popupId))
                .build();
    }

    @GetMapping
    public ResponseEntity<PopupsGetResponse> getFilteredPopups(Principal principal,
                                                               @RequestParam(name = "filter-category", defaultValue = "RECOMMENDATION") FilterCategory filterCategory,
                                                               @RequestParam(name = "search-keyword", defaultValue = "") String searchKeyword) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(popupService.getFilteredPopups(loginUser, filterCategory, searchKeyword));
    }
}
