package up.value.chefleaserver.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    ITALIAN("이탈리안"),
    MAXICAN("멕시칸"),
    DESERT("디저트"),
    TAI("타이"),
    VIETNAMESE("베트남"),
    HALAL("할랄"),
    FUSION("퓨전");

    private final String koreanLabel;
}
