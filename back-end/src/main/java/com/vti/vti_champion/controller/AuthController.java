package com.vti.vti_champion.controller;

import com.vti.vti_champion.configuration.JwtService;
import com.vti.vti_champion.dto.request.LoginRequest;
import com.vti.vti_champion.entity.RefreshToken;
import com.vti.vti_champion.entity.User;
import com.vti.vti_champion.service.interfaces.IRefreshTokenService;
import com.vti.vti_champion.service.interfaces.IUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtService  jwtService;
    private final IRefreshTokenService refreshTokenService;
    private final MessageSource messageSource;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest request, HttpServletResponse response) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserByUsername(userDetails.getUsername());

            String accessToken = jwtService.generateAccessToken(userDetails);
            RefreshToken token = refreshTokenService.createRefreshToken(user);

            Cookie cookie1 = new Cookie("refreshToken", token.getToken());
            cookie1.setHttpOnly(true);
            cookie1.setSecure(true);
            cookie1.setPath("/");
            if (request.isRememberMe()) {
                cookie1.setMaxAge(30 * 24 * 60 * 60);
            }
            else {
                cookie1.setMaxAge(24 * 60 * 60);
            }

            Cookie cookie2 = new Cookie("accessToken", accessToken);
            cookie2.setHttpOnly(true);
            cookie1.setSecure(false);
            cookie2.setPath("/");
            cookie2.setMaxAge(15 * 60);

            response.addCookie(cookie1);
            response.addCookie(cookie2);

            return ResponseEntity.ok(Map.of(
                    "message", messageSource.getMessage("login.success",null, locale),
                    "accessToken", accessToken
            ));

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("password.incorrect");
        }
    }
}
