package up.value.chefleaserver.service;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.Menu;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupCategory;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserRestaurant;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.dto.UserRestaurantsGetResponse;
import up.value.chefleaserver.dto.popup.PopupRegisterPostRequest;
import up.value.chefleaserver.dto.restaurant.RestaurantGetResponse;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationRequest;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationResponse;
import up.value.chefleaserver.repository.MenuRepository;
import up.value.chefleaserver.repository.PopupCategoryRepository;
import up.value.chefleaserver.repository.PopupRepository;
import up.value.chefleaserver.repository.RestaurantRepository;
import up.value.chefleaserver.repository.UserRestaurantRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRestaurantService {

    private final UserRestaurantRepository userRestaurantRepository;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;
    private final PopupRepository popupRepository;
    private final MenuRepository menuRepository;
    private final PopupCategoryRepository popupCategoryRepository;

    @Transactional(readOnly = true)
    public UserRestaurantsGetResponse getAllRegisteredRestaurant(User user) {
        List<UserRestaurant> userRestaurants = userRestaurantRepository.findAllByUser(user);
        return UserRestaurantsGetResponse.of(userRestaurants);
    }

    public void reserveRestaurantOrThrow(User user, RestaurantReservationRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .image(request.restaurantImage())
                .city(request.location().city())
                .district(request.location().district())
                .address(request.location().address())
                .detailedAddress(request.location().detailedAddress())
                .period(request.desiredPeriod())
                .startTime(request.desiredStartTime())
                .endTime(request.desiredEndTime())
                .hall(request.tools().hall())
                .kitchen(request.tools().hall())
                .etc(request.tools().etc())
                .user(user)
                .build();
        restaurantService.saveRestaurant(restaurant);
        UserRestaurant userRestaurant = UserRestaurant.builder()
                .user(user)
                .restaurant(restaurant)
                .build();
        userRestaurantRepository.save(userRestaurant);
    }

    public UserRestaurantReservationResponse registerUserRestaurantReservation(Long restaurantId,
                                                                               User loginUser,
                                                                               UserRestaurantReservationRequest userRestaurantReservationRequest) {
        PopupRegisterPostRequest popupRegisterPostRequest = userRestaurantReservationRequest.popupInfo();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new);
        UserRestaurant userRestaurant = UserRestaurant.create(loginUser, restaurant);
        userRestaurantRepository.save(userRestaurant);
        Popup popup = Popup.create(popupRegisterPostRequest, restaurant.getPeriod(), userRestaurant);
        popupRepository.save(popup);

        List<Menu> menus = userRestaurantReservationRequest.menus()
                .stream()
                .map(menuRegisterPostRequest -> Menu.create(menuRegisterPostRequest, popup))
                .toList();
        menuRepository.saveAll(menus);

        List<PopupCategory> popupCategories = userRestaurantReservationRequest.foodCategories()
                .stream()
                .map(koreanLabel -> PopupCategory.create(getCategoryByKoreanLabel(koreanLabel), popup))
                .toList();
        popupCategoryRepository.saveAll(popupCategories);

        return UserRestaurantReservationResponse.of(RestaurantGetResponse.of(restaurant));
    }

    private Category getCategoryByKoreanLabel(String koreanLabel) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getKoreanLabel().equals(koreanLabel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + koreanLabel));
    }
}
