package up.value.chefleaserver.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.service.PopupService;
import up.value.chefleaserver.service.UserService;

import java.security.Principal;

import static org.springframework.http.HttpStatus.OK;

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
}
