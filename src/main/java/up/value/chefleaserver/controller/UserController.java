package up.value.chefleaserver.controller;


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
import up.value.chefleaserver.dto.UserRestaurantsGetResponse;
import up.value.chefleaserver.service.UserRestaurantService;
import up.value.chefleaserver.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRestaurantService userRestaurantService;

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
}
