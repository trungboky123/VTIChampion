package com.vti.vti_champion.service.interfaces;

import com.vti.vti_champion.entity.RefreshToken;
import com.vti.vti_champion.entity.User;

public interface IRefreshTokenService {
    RefreshToken createRefreshToken(User user);
}
