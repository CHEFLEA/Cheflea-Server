package up.value.chefleaserver.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.PopupsGetResponse;
import up.value.chefleaserver.dto.UserRestaurantsGetResponse;
import up.value.chefleaserver.dto.user.RegisterUserRequest;
import up.value.chefleaserver.service.PopupLikeService;
import up.value.chefleaserver.service.UserRestaurantService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRestaurantService userRestaurantService;
    private final PopupLikeService popupLikeService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody String userId) {
        String token = userService.login(Long.valueOf(userId));
        return ResponseEntity
                .status(OK)
                .body(Map.of("Authorization", token));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<UserRestaurantsGetResponse> getAllRegisteredRestaurant(Principal principal) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userRestaurantService.getAllRegisteredRestaurant(loginUser));
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(Principal principal,
                                             @RequestBody RegisterUserRequest registerUserRequest) {
        User loginUser = userService.getUserOrException(Long.valueOf(principal.getName()));
        userService.registerUser(loginUser, registerUserRequest);
        return ResponseEntity
                .status(CREATED)
                .build();
    }
}
