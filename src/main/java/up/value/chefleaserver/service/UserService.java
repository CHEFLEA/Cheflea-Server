package up.value.chefleaserver.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.config.jwt.JwtProvider;
import up.value.chefleaserver.config.jwt.UserAuthentication;
import up.value.chefleaserver.domain.Career;
import up.value.chefleaserver.domain.Certificate;
import up.value.chefleaserver.domain.Popup;
import up.value.chefleaserver.domain.PopupLike;
import up.value.chefleaserver.domain.User;
import up.value.chefleaserver.dto.user.RegisterUserRequest;
import up.value.chefleaserver.repository.CareerRepository;
import up.value.chefleaserver.repository.CertificateRepository;
import up.value.chefleaserver.repository.UserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final CareerRepository careerRepository;
    private final CertificateRepository certificateRepository;

    public String login(Long userId) {
        User user = getUserOrException(userId);
        UserAuthentication userAuthentication = new UserAuthentication(user.getId(), null, null);
        return jwtProvider.generateJWT(userAuthentication);
    }

    public User getUserOrException(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("no user"));
    }

    @Transactional(readOnly = true)
    public Boolean isLikedByUser(User loginUser, Popup popup) {
        return popup.getPopupLikes()
                .stream()
                .map(PopupLike::getUser)
                .toList()
                .contains(loginUser);
    }

    public void registerUser(User loginUser, RegisterUserRequest registerUserRequest) {
        loginUser.registerChef(registerUserRequest);

        List<Career> careers = registerUserRequest.careers()
                .stream()
                .map(career -> Career.create(loginUser, career))
                .toList();
        careerRepository.saveAll(careers);

        List<Certificate> certificates = registerUserRequest.certificates()
                .stream()
                .map(registerCertificateRequest -> Certificate.create(loginUser, registerCertificateRequest))
                .toList();
        certificateRepository.saveAll(certificates);
    }
}
