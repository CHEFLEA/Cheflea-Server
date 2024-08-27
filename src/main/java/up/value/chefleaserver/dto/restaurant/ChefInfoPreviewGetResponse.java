package up.value.chefleaserver.dto.restaurant;

import java.util.List;
import up.value.chefleaserver.common.Category;
import up.value.chefleaserver.domain.Career;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.domain.UserCategory;

public record ChefInfoPreviewGetResponse(
        String chefName,
        String chefImage,
        String chefPhoneNumber,
        List<String> chefCareers,
        List<String> chefCategories
) {

    public static ChefInfoPreviewGetResponse of(User chef) {

        return new ChefInfoPreviewGetResponse(
                chef.getName(),
                chef.getChefImage(),
                chef.getPhoneNumber(),
                chef.getChefCareers()
                        .stream()
                        .map(Career::getDescription)
                        .toList(),
                chef.getCategories()
                        .stream()
                        .map(UserCategory::getCateory)
                        .map(Category::getKoreanLabel)
                        .toList()
        );
    }
}
