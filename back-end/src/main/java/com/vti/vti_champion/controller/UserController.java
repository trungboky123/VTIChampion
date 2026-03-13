package com.vti.vti_champion.controller;

import com.vti.vti_champion.configuration.CustomUserDetails;
import com.vti.vti_champion.dto.response.SettingResponse;
import com.vti.vti_champion.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(userDetails.getUsername());
        userResponse.setFullname(userDetails.getFullName());
        userResponse.setEmail(userDetails.getEmail());
        userResponse.setAvatarUrl(userDetails.getAvatarUrl());

        SettingResponse settingResponse = new SettingResponse();
        settingResponse.setName(userDetails.getAuthorities().iterator().next().getAuthority());

        userResponse.setRole(settingResponse);

        return  ResponseEntity.ok(userResponse);

    }
}
