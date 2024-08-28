package up.value.chefleaserver.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.Menu;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupCategory;
import up.value.chefleaserver.domain.PopupImage;
import up.value.chefleaserver.domain.Restaurant;
import up.value.chefleaserver.domain.RestaurantImage;
import up.value.chefleaserver.domain.TimeTable;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserRestaurant;
import up.value.chefleaserver.dto.RestaurantReservationRequest;
import up.value.chefleaserver.dto.UserRestaurantsGetResponse;
import up.value.chefleaserver.dto.menu.UserRestaurantReservationMenuGetResponse;
import up.value.chefleaserver.dto.popup.PopupRegisterPostRequest;
import up.value.chefleaserver.dto.popup.UserRestaurantReservationPopupGetResponse;
import up.value.chefleaserver.dto.restaurant.UserRestaurantReservationRestaurantGetResponse;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationRequest;
import up.value.chefleaserver.dto.userRestaurant.UserRestaurantReservationResponse;
import up.value.chefleaserver.repository.MenuRepository;
import up.value.chefleaserver.repository.PopupCategoryRepository;
import up.value.chefleaserver.repository.PopupImageRepository;
import up.value.chefleaserver.repository.PopupRepository;
import up.value.chefleaserver.repository.RestaurantImageRepository;
import up.value.chefleaserver.repository.RestaurantRepository;
import up.value.chefleaserver.repository.TimeTableRepository;
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
    private final PopupImageRepository popupImageRepository;
    private final RestaurantImageRepository restaurantImageRepository;
    private final TimeTableRepository timeTableRepository;

    @Transactional(readOnly = true)
    public UserRestaurantsGetResponse getAllRegisteredRestaurant(User user) {
        List<UserRestaurant> userRestaurants = userRestaurantRepository.findAllByUser(user);
        return UserRestaurantsGetResponse.of(userRestaurants);
    }

    public void reserveRestaurantOrThrow(User user, RestaurantReservationRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
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
                .price(request.desiredPrice())
                .user(user)
                .build();
        restaurantService.saveRestaurant(restaurant);

        List<RestaurantImage> restaurantImages = request.restaurantImages()
                .stream()
                .map(restaurantImage -> RestaurantImage.create(restaurantImage, restaurant))
                .toList();
        restaurantImageRepository.saveAll(restaurantImages);

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

        LocalDate popupDate = popup.getPeriod();
        LocalDateTime startDateTime = popupRegisterPostRequest.popupStartTime().atDate(popupDate);
        LocalDateTime endDateTime = popupRegisterPostRequest.popupEndTime().atDate(popupDate);
        while (startDateTime.isBefore(endDateTime)) {
            LocalDateTime timeSlotEndTime = startDateTime.plusMinutes(90);
            if (timeSlotEndTime.isAfter(endDateTime)) {
                timeSlotEndTime = endDateTime;
            }
            timeTableRepository.save(TimeTable.create(startDateTime, timeSlotEndTime, popup));
            startDateTime = timeSlotEndTime;
        }

        List<PopupImage> images = userRestaurantReservationRequest.popupInfo().popupImages()
                .stream()
                .map(image -> PopupImage.create(image, popup))
                .toList();
        popupImageRepository.saveAll(images);

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

        List<UserRestaurantReservationMenuGetResponse> userRestaurantReservationMenuGetResponses = menus.stream()
                .map(UserRestaurantReservationMenuGetResponse::of)
                .toList();
        List<String> categoriesByKoreanLabel = popupCategories.stream()
                .map(PopupCategory::getCategory)
                .map(Category::getKoreanLabel)
                .toList();
        List<String> popupImages = images.stream()
                .map(PopupImage::getImageUrl)
                .toList();

        return UserRestaurantReservationResponse.of(
                UserRestaurantReservationRestaurantGetResponse.of(restaurant),
                UserRestaurantReservationPopupGetResponse.of(popup, popupImages),
                userRestaurantReservationMenuGetResponses,
                categoriesByKoreanLabel);
    }

    private Category getCategoryByKoreanLabel(String koreanLabel) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getKoreanLabel().equals(koreanLabel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid category: " + koreanLabel));
    }

    public void deleteReservation(User loginUser, Long reservationId) {
        UserRestaurant userRestaurant = userRestaurantRepository.findById(reservationId)
                .orElseThrow(RuntimeException::new);
        if (userRestaurant.getUser().equals(loginUser)) {
            userRestaurantRepository.delete(userRestaurant);
        } else {
            throw new RuntimeException("본인의 예약이 아닙니다.");
        }
    }
}
