package com.example.tanaqolapi.security;

import com.example.tanaqolapi.model.AppUser;
import com.example.tanaqolapi.model.enums.Role;
import com.example.tanaqolapi.repository.AppUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user details from OAuth2User
        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        appUserRepository.findByEmail(email).orElseGet(() -> {
            AppUser newUser = AppUser.builder()
                .email(email)
                .username(name)
                .role(Role.CUSTOMER)
                .profile(picture)
                .build();
            return appUserRepository.save(newUser);
        });

        String token = jwtService.generateTokenForOAuth2User(oauth2User);

        String redirectUrl = "http://localhost:4200/auth/oauth2/callback" +
            "?token=" + token;

        log.info("Redirecting to: {}", redirectUrl);
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
